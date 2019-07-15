package com.kamkry.app.config;

public interface SecurityConstants {
    String SECRET = "WnZr4u7x!A%D*G-KaNdRgUkXp2s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F)J@NcR";
    long EXPIRATION_TIME = 864_000_000; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String TOKEN_HEADER= "Authorization";
    String SIGN_UP_URL = "/api/sign-up";
    String LOGIN_URL = "/login";
}
