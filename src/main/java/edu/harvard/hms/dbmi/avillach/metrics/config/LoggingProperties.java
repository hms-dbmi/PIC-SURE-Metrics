package edu.harvard.hms.dbmi.avillach.metrics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.logging")
public class LoggingProperties {

    private String basePath;
    private String fileName;
    private long maxFileSizeMB;
    private int maxHistoryDays;
    private LoggingMode mode;

    // Error log properties
    private boolean separateErrorLog;
    private String errorLogBasePath;
    private String errorLogFileName;
    private LoggingMode errorLogMode;
    private long errorLogMaxFileSizeMB;
    private int errorLogMaxHistoryDays;

    public boolean isSingleFileMode() {
        return mode == LoggingMode.SINGLE_FILE;
    }

    public boolean isSingleFileForErrorsMode() {
        return errorLogMode == LoggingMode.SINGLE_FILE;
    }

    public boolean isSeparateErrorLog() {
        return separateErrorLog;
    }

    public void setSeparateErrorLog(boolean separateErrorLog) {
        this.separateErrorLog = separateErrorLog;
    }

    public String getErrorLogBasePath() {
        return errorLogBasePath;
    }

    public void setErrorLogBasePath(String errorLogBasePath) {
        this.errorLogBasePath = errorLogBasePath;
    }

    public String getErrorLogFileName() {
        return errorLogFileName;
    }

    public void setErrorLogFileName(String errorLogFileName) {
        this.errorLogFileName = errorLogFileName;
    }

    public LoggingMode getErrorLogMode() {
        return errorLogMode;
    }

    public void setErrorLogMode(LoggingMode errorLogMode) {
        this.errorLogMode = errorLogMode;
    }

    public long getErrorLogMaxFileSizeMB() {
        return errorLogMaxFileSizeMB;
    }

    public void setErrorLogMaxFileSizeMB(long errorLogMaxFileSizeMB) {
        this.errorLogMaxFileSizeMB = errorLogMaxFileSizeMB;
    }

    public int getErrorLogMaxHistoryDays() {
        return errorLogMaxHistoryDays;
    }

    public void setErrorLogMaxHistoryDays(int errorLogMaxHistoryDays) {
        this.errorLogMaxHistoryDays = errorLogMaxHistoryDays;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getMaxFileSizeMB() {
        return maxFileSizeMB;
    }

    public void setMaxFileSizeMB(long maxFileSizeMB) {
        this.maxFileSizeMB = maxFileSizeMB;
    }

    public int getMaxHistoryDays() {
        return maxHistoryDays;
    }

    public void setMaxHistoryDays(int maxHistoryDays) {
        this.maxHistoryDays = maxHistoryDays;
    }

    public LoggingMode getMode() {
        return mode;
    }

    public void setMode(LoggingMode mode) {
        this.mode = mode;
    }
}