package com.example.projectdeploy.Notification.EventListener;

import com.example.projectdeploy.Notification.FCMNofication.FCMNoficiation;
import com.example.projectdeploy.Notification.Model.AppNotification;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CommunityNotificationListener {

    @Autowired
    FCMNoficiation fcmNoficiation;

    @EventListener
    public void onEntityCreated(CommunityCreatedEvent event) throws FirebaseMessagingException {
        // Perform actions when MyEntity is created
        AppNotification entity = event.getAppNotification();
        String deviceToken=entity.getToUser().getRegistrationToken();
        fcmNoficiation.SendNotificationToOneDevice(deviceToken,entity);
    }
}
