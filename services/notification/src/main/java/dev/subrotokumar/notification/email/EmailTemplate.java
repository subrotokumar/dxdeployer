package dev.subrotokumar.notification.email;

import lombok.Getter;

public enum EmailTemplate {
    LOGIN_MAGICLINK_EMAIL("login-magiclink.html", "Loginin to DxDeployer");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    private EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}   
