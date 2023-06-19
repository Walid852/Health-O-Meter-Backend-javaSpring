package com.example.projectdeploy.Notification.FCMNofication;

import com.example.projectdeploy.Notification.Model.AppNotification;
import com.example.projectdeploy.Notification.Model.NotificationRequest;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class FCMNoficiation {

    public void SendNotificationToOneDevice(String registrationToken) throws FirebaseMessagingException {
        // See documentation on defining a message payload.
        Notification notification = Notification.builder()
                .setTitle("Title")
                .setBody("Body")
                .build();
        Message message = Message.builder()
                .setNotification(notification)
                .putData("content","walid")
                .putData("KKKKK","GGGGGG")
                .setToken(registrationToken)
                .build();
        // Send a message to the device corresponding to the provided
        // registration token.
                String response = FirebaseMessaging.getInstance().send(message);
        // Response is a message ID string.
                System.out.println("Successfully sent message: " + response);
    }
    public void SendNotificationToMultiDevice(List<String> registrationTokens ) throws FirebaseMessagingException {
        MulticastMessage message = MulticastMessage.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .addAllTokens(registrationTokens)
                .build();
        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
        // See the BatchResponse reference documentation
        // for the contents of response.
        System.out.println(response.getSuccessCount() + " messages were sent successfully");
    }
}
