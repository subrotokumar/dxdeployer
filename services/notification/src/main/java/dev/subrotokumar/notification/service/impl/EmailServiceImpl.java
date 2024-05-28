package dev.subrotokumar.notification.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import dev.subrotokumar.notification.dto.EmailDetailDto;
import dev.subrotokumar.notification.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;
 
    @Value("${spring.mail.username}") private String sender;
 
    @Override
    public boolean sendSimpleMail(EmailDetailDto details)
    {
        try {
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // emailSender.send(mailMessage);
            return true;
        }
        catch (MailException e) {
            return false;
        }
    }
}