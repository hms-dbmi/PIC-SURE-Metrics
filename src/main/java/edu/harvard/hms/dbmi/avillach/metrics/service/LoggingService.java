package edu.harvard.hms.dbmi.avillach.metrics.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.hms.dbmi.avillach.metrics.config.LoggingProperties;
import edu.harvard.hms.dbmi.avillach.metrics.model.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    private final LoggingProperties loggingProperties;
    private final ObjectMapper objectMapper;

    public LoggingService(LoggingProperties loggingProperties, ObjectMapper objectMapper) {
        this.loggingProperties = loggingProperties;
        this.objectMapper = objectMapper;
    }

    @Async
    public void logUserInteraction(LogEntry logEntry) {
        writeLogEntry(logEntry, false);
    }

    @Async
    public void logErrorInteraction(LogEntry logEntry) {
        writeLogEntry(logEntry, true);
    }

    private void writeLogEntry(LogEntry logEntry, boolean isErrorLog) {
        try {
            String logFilePath = determineLogFilePath(isErrorLog);
            Files.createDirectories(Paths.get(getBasePathForLogType(isErrorLog)));
            String jsonLogEntry = objectMapper.writeValueAsString(logEntry) + "\n";
            Files.write(
                    Paths.get(logFilePath),
                    jsonLogEntry.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            logger.error("Failed to log " + (isErrorLog ? "error" : "user") + " interaction", e);
        }
    }

    private String getBasePathForLogType(boolean isErrorLog) {
        if (isErrorLog) {
            return loggingProperties.getErrorLogBasePath() != null
                    ? loggingProperties.getErrorLogBasePath()
                    : loggingProperties.getBasePath();
        }
        return loggingProperties.getBasePath();
    }

    private String determineLogFilePath(boolean isErrorLog) {
        String basePath = getBasePathForLogType(isErrorLog);
        String fileName;
        boolean isSingleFileMode;

        if (isErrorLog) {
            fileName = loggingProperties.getErrorLogFileName();
            isSingleFileMode = loggingProperties.isSingleFileForErrorsMode();
        } else {
            fileName = loggingProperties.getFileName();
            isSingleFileMode = loggingProperties.isSingleFileForErrorsMode();
        }

        if (isSingleFileMode) {
            return Paths.get(basePath, fileName).toString();
        } else {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
            String rollingFileName = String.format("%s-%s.json",
                    fileName.substring(0, fileName.lastIndexOf('.')),
                    timestamp);
            return Paths.get(basePath, rollingFileName).toString();
        }
    }
}