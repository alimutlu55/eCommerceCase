package com.trendyol.eCommerceCase.constants;

public class SecurityConstants {
    public static final String SECRET = "happy";
    public static final long EXPIRATION_TIME = 423_000_000; // 5 gün
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String[] PUBLIC_URLS = { "/login", "/sign-up"};
    private static final String[] AUTH_LIST = {"**/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**"};
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
}
