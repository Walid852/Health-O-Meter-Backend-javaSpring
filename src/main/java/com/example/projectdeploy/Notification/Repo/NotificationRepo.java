package com.example.projectdeploy.Notification.Repo;

import com.example.projectdeploy.Notification.Model.AppNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationRepo extends JpaRepository<AppNotification, UUID> {
    @Query("select N from AppNotification N where N.id=?1 ")
    AppNotification FindNotificationById(UUID id);
    @Query("select N from AppNotification N where N.user.id=?1 ")
    AppNotification FindNotificationByUserId(UUID id);
    @Query("select N from AppNotification N where N.user.id=?1 and N.readd=?2 ")
    AppNotification FindNotificationByUserId(UUID id, boolean read);



}