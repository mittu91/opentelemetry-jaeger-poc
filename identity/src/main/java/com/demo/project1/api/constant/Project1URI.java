package com.demo.project1.api.constant;

public class Project1URI {

    public static final String DEMO_USER = "/demo/protected/user";
    public static final String DEMO_USER_BY_ID = "/demo/protected/user/{id}";

    public static final String AUTHORIZE = "/demo/private/authorize";

    public static final String LOGIN = "/demo/public/user/login";
    public static final String RESET_PASSWORD = "/demo/protected/user/password/reset";
    public static final String LOG_OUT = "/demo/protected/user/log-out";
}
