package dev.subrotokumar.notification.service;

import java.util.List;

import dev.subrotokumar.notification.entity.Notification;

public interface NotificationService {
    public List<Notification> findMyNotifications(int userId);
}
