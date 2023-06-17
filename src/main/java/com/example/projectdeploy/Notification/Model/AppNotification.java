package com.example.projectdeploy.Notification.Model;

import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
public class AppNotification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private User FromUser;
    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private User ToUser;
    String title;
    String message;
    UUID url;
    TypeUrl typeUrl;
    String photo;
    boolean readd =false;
    Date notificationDate;

    public AppNotification(User fromUser, User toUser, String title, String message, UUID url, TypeUrl typeUrl, String photo, Date notificationDate) {

        FromUser = fromUser;
        ToUser = toUser;
        this.title = title;
        this.message = message;
        this.url = url;
        this.typeUrl = typeUrl;
        this.photo = photo;
        this.notificationDate = notificationDate;
    }

    public AppNotification() {

    }
}
