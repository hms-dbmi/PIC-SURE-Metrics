package edu.harvard.hms.dbmi.avillach.metrics.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class LogEntry {
    @JsonProperty("event_id")
    private UUID eventId;

    @JsonProperty("action")
    private String action;

    @JsonProperty("action_metadata")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> actionMetadata;

    @JsonProperty("timestamp")
    private Instant timestamp;

    @JsonProperty("user")
    private UserInfo user;

    @JsonProperty("environment")
    private EnvInfo environment;

    @JsonProperty("ip_address")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$", message = "Invalid IP address format")
    private String ipAddress;

    @JsonProperty("other_metadata")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> otherMetadata;

    @JsonProperty("error_details")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDetails errorDetails;

    public LogEntry() {
        this.timestamp = Instant.now();
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getActionMetadata() {
        return actionMetadata;
    }

    public void setActionMetadata(Map<String, Object> actionMetadata) {
        this.actionMetadata = actionMetadata;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public EnvInfo getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvInfo environment) {
        this.environment = environment;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Map<String, Object> getOtherMetadata() {
        return otherMetadata;
    }

    public void setOtherMetadata(Map<String, Object> otherMetadata) {
        this.otherMetadata = otherMetadata;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }
}
