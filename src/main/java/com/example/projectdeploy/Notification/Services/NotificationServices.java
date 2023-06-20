package com.example.projectdeploy.Notification.Services;

import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.Donate.Repo.DonateRepo;
import com.example.projectdeploy.Notification.Model.*;
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
    PostRepo postRepo;
    @Autowired
    DonateRepo donateRepo;
    @Autowired
    NotificationRepo notificationRepo;
    public Response<AppNotification> AddNotification(NotificationRequest notificationRequest){
        try {
            User FromUser;
            User ToUser;
            if(userRepo.findById(notificationRequest.getFromUserId()).isPresent()&&userRepo.findById(notificationRequest.getToUserId()).isPresent()){
                FromUser=userRepo.findById(notificationRequest.getFromUserId()).get();
                ToUser=userRepo.findById(notificationRequest.getToUserId()).get();
            }
            else {
                return new com.example.projectdeploy.Shared.Response<>(false, "Users not Found");
            }
            AppNotification notification=new AppNotification(FromUser,ToUser,notificationRequest.getTitle(),notificationRequest.getMessage(),
                    notificationRequest.getUrl(),notificationRequest.getTypeUrl(),notificationRequest.getPhoto(),notificationRequest.getNotificationDate());
            System.out.println(notification);
            notificationRepo.saveAndFlush(notification);
            List<AppNotification> appNotificationList=new LinkedList<>();
            appNotificationList.add(notification);
            return new Response<>(true, StaticsText.MessageForTest("notification", "added"),appNotificationList);
        }catch (Exception e){
            System.out.println(e);
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
    public ResponseForNotification OpenNotification(UUID notificationId){
        try {
            AppNotification appNotification=notificationRepo.FindNotificationById(notificationId);
            if(appNotification.getTypeUrl()==TypeUrl.Post){
                return new ResponseForNotification(true, "successfully Retrieved Post",postRepo.findPostById(appNotification.getUrl()),null);
            }else if(appNotification.getTypeUrl()==TypeUrl.Donate) {
                return new ResponseForNotification(true, "successfully Retrieved Donate", null, donateRepo.findDonateById(appNotification.getUrl()));
            }
            else if(appNotification.getTypeUrl()==TypeUrl.Non) {
                return new ResponseForNotification(false, "not have reference for this notification",null,null);
            }
            else {
                return new ResponseForNotification(true, "error",null,null);
            }
        }catch (Exception e){
            return new ResponseForNotification(false, StaticsText.MessageForTestError(),null,null);
        }
    }
}
