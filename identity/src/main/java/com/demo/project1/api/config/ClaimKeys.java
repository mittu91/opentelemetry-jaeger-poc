package com.demo.project1.api.config;

/**
 * {@code ClaimKeys} is a class that contains constants for claims
 *
 * @author Nisheeth Shah
 * @since 1.0.0.RELEASE September 27, 2018
 */
public final class ClaimKeys {

    /**
     * For user details in the claims of access token
     */
    public static final String USER_DETAILS = "userDetails";

    public static final String USER_ID = "userId";

    /**
     * For user roles in the claims of access token
     */
    public static final String ROLES = "roles";

    /**
     * For request scope in the claims of access token
     */
    public static final String SCOPE = "scope";

    public static final String SESSION_ID = "sessionId";

    private ClaimKeys() {
        //Private no argument constructor to prevent instantiation of this class
    }
}
