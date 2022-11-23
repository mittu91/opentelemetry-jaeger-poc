package com.demo.project1.api.config;

import com.demo.project1.api.model.request.UserDetails;
import com.demo.project1.exception.InvalidAuthTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {

    private static final String BEARER = "Bearer ";

    private final JWTSetting jwtSetting;

    public JWTUtils(JWTSetting jwtSetting) {
        this.jwtSetting = jwtSetting;
    }

    public static String extractToken(String authorizationHeader) {
        if (!authorizationHeader.startsWith(BEARER)) {
            throw new RuntimeException("JWT cannot be empty");
        }
        return authorizationHeader.substring(BEARER.length());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSetting.getTokenSigningKey()).parseClaimsJws(token).getBody();
    }

    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public Boolean isTokenExpired(String token) {
        Date expiration;
        try {
            expiration = getExpirationDateFromToken(token);
            return expiration != null && expiration.before(new Date());
        } catch (ExpiredJwtException ex) {
            return true;
        }
    }

    public String generateToken(UserDetails user, boolean neverExpire) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ClaimKeys.USER_ID, user.getUserId());
        claims.put(ClaimKeys.SESSION_ID, user.getSessionId());
//        claims.put(ClaimKeys.ROLES, user.getRoles().stream().map(String::valueOf).collect(Collectors.toList()));
        Date expiry = neverExpire ? null : Date.from(Instant.now().plus(jwtSetting.getTokenExpirationTime(), ChronoUnit.MINUTES));
        return doGenerateToken(claims, String.valueOf(user.getUserId()), expiry);
    }

    /**
     * Generates token for the given claims, username and expiry date
     *
     * @param claims   claims
     * @param username username/email
     * @param expiry   expiry date
     * @return access token
     */
    private String doGenerateToken(Map<String, Object> claims, String username, Date expiry) {
        Date createdDate = new Date();
        if (expiry != null) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(createdDate)
                    .setExpiration(expiry)
                    .signWith(SignatureAlgorithm.HS512, jwtSetting.getTokenSigningKey())
                    .compact();
        } else {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(createdDate)
                    .signWith(SignatureAlgorithm.HS512, jwtSetting.getTokenSigningKey())
                    .compact();
        }
    }

    /**
     * @param user        user details
     * @param neverExpire true if never expires
     * @return refresh token
     */
    public String generateRefreshToken(UserDetails user, boolean neverExpire) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ClaimKeys.SCOPE, Scope.REFRESH_TOKEN);
        claims.put(ClaimKeys.USER_ID, user.getUserId());
        claims.put(ClaimKeys.SESSION_ID, user.getSessionId());
        claims.put(ClaimKeys.ROLES, Collections.emptyList());
        Date expiry = Date.from(Instant.now().plus(jwtSetting.getRefreshTokenExpTime(), ChronoUnit.MINUTES));
        return doGenerateToken(claims, user.getEmail(), expiry);
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(JWTUtils.extractToken(token));
        String userIdStr = claims.getSubject();
        if (!StringUtils.hasLength(userIdStr) || isTokenExpired(JWTUtils.extractToken(token))) {
            throw new InvalidAuthTokenException("Invalid authorization token.");
        }
        return Long.valueOf(userIdStr);
    }

    public String getSessionIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(JWTUtils.extractToken(token));
        String sessionId = (String) claims.get("sessionId");
        if (sessionId == null || isTokenExpired(JWTUtils.extractToken(token))) {
            throw new InvalidAuthTokenException("Invalid authorization token.");
        }
        return sessionId;
    }

    public String getUsernameFromToken(String token) {
        return (String) getAllClaimsFromToken(token).getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
