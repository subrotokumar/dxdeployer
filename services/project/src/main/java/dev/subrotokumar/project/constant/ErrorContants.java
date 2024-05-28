package dev.subrotokumar.project.constant;

public class ErrorContants {
    private ErrorContants() {}
    
    public final static String UNAUTHORIZED_OPERATION = "Un-authorized operation";
    public final static String PROJECT_NOT_FOUND = "PROJECT NOT FOUND";
    public final static String USER_NOT_EXIST = "User not exist";

    public final static String EMPTY_TITLE = "title field can't be empty";
    public final static String EMPTY_DESCRIPTION = "description field can't be empty";
    public final static String INVALID_GITHUB_URL = "Invalid github url";
    public final static String INVALID_PROJECT_TYPE = "Invalid Project Type. Value allowed: VANILLA, REACT";
}
