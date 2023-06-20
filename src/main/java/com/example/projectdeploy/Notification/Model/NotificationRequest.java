package com.example.projectdeploy.Notification.Model;

import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class NotificationRequest {
    private UUID FromUserId;
    private UUID ToUserId;
    private String title=null;
    private String message=null;
    private UUID url=null;
    private TypeUrl typeUrl;
    private String photo=null;
    private Date notificationDate;

    public NotificationRequest(UUID fromUserId, UUID toUserId, String title, String message, UUID url, TypeUrl typeUrl, String photo, Date notificationDate) {
        FromUserId = fromUserId;
        ToUserId = toUserId;
        this.title = title;
        this.message = message;
        this.url = url;
        this.typeUrl = typeUrl;
        this.photo = photo;
        this.notificationDate = notificationDate;
    }
}
