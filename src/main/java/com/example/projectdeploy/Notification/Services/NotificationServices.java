package com.example.projectdeploy.Notification.Services;

import com.example.projectdeploy.Notification.Model.AppNotification;
import com.example.projectdeploy.Notification.Model.ConstantMessage;
import com.example.projectdeploy.Notification.Model.NotificationRequest;
import com.example.projectdeploy.Notification.Model.TypeUrl;
import com.example.projectdeploy.Notification.Repo.NotificationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationServices {
    @Autowired
    UserRepo userRepo;
    @Autowired
    NotificationRepo notificationRepo;
    public Response<AppNotification> AddNotification(NotificationRequest notificationRequest){
        try {
            User FromUser=userRepo.findByUserId(notificationRequest.getFromUserId());
            User ToUser=userRepo.findByUserId(notificationRequest.getToUserId());
            AppNotification notification=new AppNotification(FromUser,ToUser,notificationRequest.getTitle(),notificationRequest.getMessage(),
                    notificationRequest.getUrl(),notificationRequest.getTypeUrl(),notificationRequest.getPhoto(),notificationRequest.getNotificationDate());
            notificationRepo.save(notification);
            List<AppNotification> appNotificationList=new LinkedList<>();
            appNotificationList.add(notification);
            return new Response<>(true, StaticsText.MessageForTest("notification", "added"),appNotificationList);
        }catch (Exception e){
            return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTestError());
        }
   }
   public Response<AppNotification> getMyNotification(UUID userId){
        try {
            List<AppNotification> appNotificationList = notificationRepo.FindNotificationByUserId(userId);
            return new Response<>(true, StaticsText.MessageForTest("notification", " retrived"), appNotificationList);
        }catch (Exception e){
            return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<AppNotification> getMyNotificationNotRead(UUID userId){
        try {
            List<AppNotification> appNotificationList=notificationRepo.FindNotificationByUserIdAndRead(userId,false);
            return new Response<>(true, StaticsText.MessageForTest("notification", " retrived"), appNotificationList);

        }catch (Exception e){
            return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTestError());
        }
    }
}
