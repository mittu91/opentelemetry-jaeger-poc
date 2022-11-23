package com.demo.project2.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * {@code PathSettings} class provide path related properties and its getter-setter methods
 *
 * @author Nisheeth Shah
 * @since 1.0.0.RELEASE September 27, 2018
 */
@Configuration
@ConfigurationProperties("gateway.url")
public class PathSettings {

    private String[] publicPath;

    private String[] authenticatedPath;

    private String[] privatePath;

    private String logoutPath;

    public String[] getPublicPath() {
        return publicPath;
    }

    public void setPublicPath(String[] publicPath) {
        this.publicPath = publicPath;
    }

    public String[] getAuthenticatedPath() {
        return authenticatedPath;
    }

    public void setAuthenticatedPath(String[] authenticatedPath) {
        this.authenticatedPath = authenticatedPath;
    }

    public String[] getPrivatePath() {
        return privatePath;
    }

    public void setPrivatePath(String[] privatePath) {
        this.privatePath = privatePath;
    }

    public String getLogoutPath() {
        return logoutPath;
    }

    public void setLogoutPath(String logoutPath) {
        this.logoutPath = logoutPath;
    }
}
