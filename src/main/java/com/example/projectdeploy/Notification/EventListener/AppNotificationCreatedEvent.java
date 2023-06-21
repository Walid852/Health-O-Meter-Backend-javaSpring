package com.example.projectdeploy.Notification.EventListener;

import com.example.projectdeploy.Notification.Model.AppNotification;
import org.springframework.context.ApplicationEvent;


public class AppNotificationCreatedEvent extends ApplicationEvent {

    private final AppNotification appNotification;

    public AppNotificationCreatedEvent(Object source, AppNotification appNotification) {
        super(source);
        this.appNotification = appNotification;
    }

    public AppNotification getAppNotification() {
        return appNotification;
    }
}
