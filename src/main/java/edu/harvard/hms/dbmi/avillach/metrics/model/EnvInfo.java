package edu.harvard.hms.dbmi.avillach.metrics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvInfo {

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("env")
    private String env;

    @JsonProperty("source")
    private String source;

    @JsonProperty("user_agent")
    private String userAgent;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
