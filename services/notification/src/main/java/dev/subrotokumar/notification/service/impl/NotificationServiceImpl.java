package dev.subrotokumar.notification.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.subrotokumar.notification.entity.Notification;
import dev.subrotokumar.notification.repository.NotificationRepository;
import dev.subrotokumar.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements  NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> findMyNotifications(int userId){
        var response = notificationRepository.findByUserId(userId);
        return response != null ? response : new ArrayList<>();
    }
}
