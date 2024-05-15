package dev.subrotokumar.accounts.constants;

public class ErrorConstanst {
    private ErrorConstanst(){}
    //! Username Error
    public static final String INVALID_USERNAME_LENGTH = "username length should be minimum of 6 character";
    public static final String EMPTY_USERNAME = "username can't be a null or empty";


    //! Email Error
    public static final String INVALID_EMAIL = "invalid email address";

    //! Password Error
    public static final String INVALID_PASSWORD_LENGTH = "password length should be minimum of 6 character";
    public static final String EMPTY_PASSWORD = "password can't be a null or empty";
}
