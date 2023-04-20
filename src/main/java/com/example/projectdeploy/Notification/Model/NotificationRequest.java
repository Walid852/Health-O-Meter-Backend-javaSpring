package com.example.projectdeploy.Notification.Model;

import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class NotificationRequest {
    private UUID userId;
    private String title=null;
    private String message=null;
    private String url=null;
    private String photo=null;
    private Date notificationDate;

    public NotificationRequest(UUID userId, String title, String message, String url, String photo, Date notificationDate) {
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.url = url;
        this.photo = photo;
        this.notificationDate = notificationDate;
    }
}
