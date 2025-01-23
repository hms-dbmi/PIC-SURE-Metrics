package edu.harvard.hms.dbmi.avillach.metrics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatusCode;

public class ErrorDetails {

    @JsonProperty("status_code")
    private HttpStatusCode statusCode;

    @JsonProperty("message")
    private String message;

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
