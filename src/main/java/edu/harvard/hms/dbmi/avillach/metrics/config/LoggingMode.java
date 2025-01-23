package edu.harvard.hms.dbmi.avillach.metrics.config;

public enum LoggingMode {
    /**
     * Append all logs to a single file
     */
    SINGLE_FILE,

    /**
     * Create rolling log files based on size or time
     */
    ROLLING_FILE
}
