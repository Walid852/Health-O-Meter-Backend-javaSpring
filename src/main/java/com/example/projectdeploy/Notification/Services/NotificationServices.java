package com.example.projectdeploy.Notification.Services;

import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.Donate.Repo.DonateNotifiedRepo;
import com.example.projectdeploy.Donate.Repo.DonateRepo;
import com.example.projectdeploy.Notification.EventListener.AppNotificationCreatedEvent;
import com.example.projectdeploy.Notification.Model.*;
import com.example.projectdeploy.Notification.Repo.NotificationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    DonateNotifiedRepo donateNotifiedRepo;
    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
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
            System.out.println(FromUser.getId());
            System.out.println(ToUser.getId());
            AppNotification notification=new AppNotification(FromUser,ToUser,notificationRequest.getTitle(),notificationRequest.getMessage(),
                    notificationRequest.getUrl(),notificationRequest.getTypeUrl(),notificationRequest.getPhoto(),notificationRequest.getNotificationDate());
            System.out.println(notification);
            notificationRepo.save(notification);
            // Publish the event
            eventPublisher.publishEvent(new AppNotificationCreatedEvent(this, notification));
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
    public Response<AppNotification> getAllNotification(){
        try {
            List<AppNotification> appNotificationList = notificationRepo.findAll();
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
            appNotification.setReadd(true);
            notificationRepo.save(appNotification);
            if(appNotification.getTypeUrl()==TypeUrl.Post){
                return new ResponseForNotification(true, "successfully Retrieved Post",postRepo.findPostById(appNotification.getUrl()),null,null);
            }else if(appNotification.getTypeUrl()==TypeUrl.Donate) {
                return new ResponseForNotification(true, "successfully Retrieved Donate", null, donateRepo.findDonateById(appNotification.getUrl()),null);
            }
            else if(appNotification.getTypeUrl()==TypeUrl.DonateRequest) {
                return new ResponseForNotification(true, "successfully Retrieved Donate", null,null,donateNotifiedRepo.findDonateById(appNotification.getUrl()));
            }
            else if(appNotification.getTypeUrl()==TypeUrl.Noneeeee) {
                return new ResponseForNotification(false, "not have reference for this notification",null,null,null);
            }
            else {
                return new ResponseForNotification(false, "not have reference for this notification",null,null,null);
            }
        }catch (Exception e){
            return new ResponseForNotification(false, StaticsText.MessageForTestError(),null,null,null);
        }
    }
}
