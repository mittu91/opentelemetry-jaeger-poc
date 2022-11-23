package com.demo.project1.api.model.response;

public class BaseResponse {
    private String targetURL;
    private String requestURL;
    private Integer connectionStatus;

    public BaseResponse() {
    }

    private BaseResponse(Builder builder) {
        setTargetURL(builder.targetURL);
        setRequestURL(builder.requestURL);
        setConnectionStatus(builder.connectionStatus);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(BaseResponse copy) {
        Builder builder = new Builder();
        builder.targetURL = copy.getTargetURL();
        builder.requestURL = copy.getRequestURL();
        builder.connectionStatus = copy.getConnectionStatus();

        return builder;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public Integer getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(Integer connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public static final class Builder {
        private String targetURL;
        private String requestURL;
        private Integer connectionStatus;

        public Builder() {
        }


        public Builder targetURL(String targetURL) {
            this.targetURL = targetURL;
            return this;
        }

        public Builder requestURL(String requestURL) {
            this.requestURL = requestURL;
            return this;
        }

        public Builder connectionStatus(Integer connectionStatus) {
            this.connectionStatus = connectionStatus;
            return this;
        }

        public BaseResponse build() {
            return new BaseResponse(this);
        }
    }
}

