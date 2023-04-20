package com.example.projectdeploy.Notification.Services;

import com.example.projectdeploy.Notification.Model.AppNotification;
import com.example.projectdeploy.Notification.Model.NotificationRequest;
import com.example.projectdeploy.Notification.Repo.NotificationRepo;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices {
    @Autowired
    UserRepo userRepo;
    @Autowired
    NotificationRepo notificationRepo;
    public AppNotification AddNotification(NotificationRequest notificationRequest){
        User user=userRepo.findByUserId(notificationRequest.getUserId());
        AppNotification notification=new AppNotification(user,notificationRequest.getTitle(),notificationRequest.getMessage(),
                notificationRequest.getUrl(),notificationRequest.getPhoto(),notificationRequest.getNotificationDate());
        notificationRepo.save(notification);
        return notification;
    }
}
