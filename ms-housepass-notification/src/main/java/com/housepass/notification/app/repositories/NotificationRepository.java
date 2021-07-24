package com.housepass.notification.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.housepass.notification.app.entities.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String>{

}
