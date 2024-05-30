package dev.subrotokumar.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.subrotokumar.notification.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification,String> {
    
}
