package edu.harvard.hms.dbmi.avillach.metrics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UserInfo {
    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("email")
    private String email;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("logged_in")
    private Boolean isLoggedIn;

    @JsonProperty("type")
    private String type;

    @JsonProperty("session_id")
    private UUID sessionId;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }
}
