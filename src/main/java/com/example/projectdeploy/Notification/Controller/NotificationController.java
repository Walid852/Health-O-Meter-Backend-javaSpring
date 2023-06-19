package com.example.projectdeploy.Notification.Controller;
import com.example.projectdeploy.Notification.FCMNofication.FCMNoficiation;
import com.example.projectdeploy.Notification.Model.AppNotification;
import com.example.projectdeploy.Notification.Model.NotificationRequest;
import com.example.projectdeploy.Notification.Model.ResponseForNotification;
import com.example.projectdeploy.Notification.Services.NotificationServices;
import com.example.projectdeploy.Shared.Response;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/Notification")
@RestController
public class NotificationController {
    @Autowired
    NotificationServices notificationServices;
    @Autowired
    FCMNoficiation fcmNoficiation;
    @PostMapping(path="/AddNotification")
    public @ResponseBody
    Response<AppNotification> AddNotification(@RequestBody  NotificationRequest notificationRequest){
        return notificationServices.AddNotification(notificationRequest);
    }
    @GetMapping(path="/getMyNotification")
    public @ResponseBody
    Response<AppNotification> getMyNotification(@RequestParam  UUID userId){
        return notificationServices.getMyNotification(userId);
    }
    @GetMapping(path="/getMyNotificationNotRead")
    public @ResponseBody
    Response<AppNotification> getMyNotificationNotRead(@RequestParam  UUID userId){
        return notificationServices.getMyNotificationNotRead(userId);
    }
    @GetMapping(path="/OpenNotification")
    public @ResponseBody
    ResponseForNotification OpenNotification(@RequestParam UUID notificationId){
        return notificationServices.OpenNotification(notificationId);
    }
    @PostMapping(path="/SendNotificationToOneDevice")
    public @ResponseBody
    void SendNotificationToOneDevice(@RequestParam String registrationToken) throws FirebaseMessagingException {
        fcmNoficiation.SendNotificationToOneDevice(registrationToken);
    }
}
