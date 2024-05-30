package dev.subrotokumar.accounts.constants;

public class Constants {
    private Constants(){}

    public final static String USERNAME = "X-USER-NAME";
    public final static String USER_ID = "X-USER-ID";
    public final static String USER_ROLE = "X-USER-ROLE";

    public final static String[] secureEndpoint = {
        "/api/v1/account/auth/register",
        "/api/v1/account/auth/login",
        "/api/v1/account/info/health",
        "/api/v1/account/swagger-ui.html",
        "/api/v1/account/v3/api-docs",
        "/api/v1/account/auth/magiclink",
        "/api/v1/account/auth/magiclink/verify",
        "/api/v1/account/swagger-ui/index.html",
        "/swagger-ui/index.html"
        // "/api/v1/account/swagger-ui.html",
        // "/swagger-ui/index.html"
    };
}
