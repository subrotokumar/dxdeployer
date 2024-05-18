package dev.subrotokumar.notification.service;

import dev.subrotokumar.notification.dto.EmailDetailDto;

public interface EmailService {
    boolean sendSimpleMail(EmailDetailDto details);
}