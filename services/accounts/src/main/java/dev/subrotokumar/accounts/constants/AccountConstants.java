package dev.subrotokumar.accounts.constants;

public class AccountConstants {

    private AccountConstants() {}

    public static final String INFO_API_PREFIX = "/api/v1/account";
    public static final String AUTH_API_PREFIX = "/api/v1/account/auth";
    public static final String USER_API_PREFIX = "/api/v1/account/user";

    public static final short STATUS_200 = 200;
    public static final String MASSAGE_200 = "Account created successfully";

    public static final short STATUS_201 = 201;
    public static final String MASSAGE_201 = "Request processed successfully";

    public static final short STATUS_500 = 201;
    public static final String MASSAGE_500 = "An error occured. Please try again";
}
