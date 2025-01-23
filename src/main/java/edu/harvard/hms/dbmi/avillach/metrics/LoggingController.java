package edu.harvard.hms.dbmi.avillach.metrics;

import edu.harvard.hms.dbmi.avillach.metrics.model.LogEntry;
import edu.harvard.hms.dbmi.avillach.metrics.service.LoggingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/")
public class LoggingController {

    private final LoggingService loggingService;

    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @PostMapping("/actions")
    public ResponseEntity<String> logUserInteraction(@Valid @RequestBody LogEntry logEntry) {
        loggingService.logUserInteraction(logEntry);
        return ResponseEntity.ok("Log entry recorded successfully");
    }

    @PostMapping("/errors")
    public ResponseEntity<String> logErrorInteraction(@Valid @RequestBody LogEntry logEntry) {
        loggingService.logErrorInteraction(logEntry);
        return ResponseEntity.ok("Error log entry recorded successfully");
    }
}