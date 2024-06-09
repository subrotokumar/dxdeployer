package dev.subrotokumar.notification.constants;

public class Constants {
    private Constants(){}

    public final static String PROJECT_API_PREFIX = "/api/v1/project";
    public final static String INFO_API_PREFIX = "/api/v1/project/info";

    public final static String USERNAME = "X-USER-NAME";
    public final static String USER_ID = "X-USER-ID";
    public final static String USER_ROLE = "X-USER-ROLE";

    public final static String GITHUB_URL_REGEX = "https?:\\/\\/(?:www\\.)?github\\.com\\/[a-zA-Z0-9-]+\\/[a-zA-Z0-9-]+";
}
