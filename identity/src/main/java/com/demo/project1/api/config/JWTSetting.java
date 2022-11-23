/*
 * Copyright (C) 2017 Nishith Shah
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.demo.project1.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "app.security.jwt")
public class JWTSetting {

    private long tokenExpirationTime;


    private String tokenIssuer;


    private String tokenSigningKey;


    private long refreshTokenExpTime;

    /**
     * Getter method for token expiry time.
     *
     * @return token expiry time
     */
    public long getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    /**
     * Setter method for token expiry time
     *
     * @param tokenExpirationTime token expiry time
     */
    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    /**
     * Getter method for token issuer
     *
     * @return token issuer
     */
    public String getTokenIssuer() {
        return tokenIssuer;
    }

    /**
     * Setter method for token issuer
     *
     * @param tokenIssuer token issuer
     */
    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    /**
     * Getter method for token signing key
     *
     * @return token signing key
     */
    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    /**
     * Setter method for token signing key
     *
     * @param tokenSigningKey token signing key
     */
    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }

    /**
     * Getter method for refresh token expiry time
     *
     * @return refresh token expiry time
     */
    public long getRefreshTokenExpTime() {
        return refreshTokenExpTime;
    }

    /**
     * Setter method for refresh token expiry time
     *
     * @param refreshTokenExpTime refresh token expiry time
     */
    public void setRefreshTokenExpTime(Integer refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }

}
