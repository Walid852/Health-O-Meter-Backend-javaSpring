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
    private User user;
    String title;
    String message;
    String url;
    String photo;
    boolean readd =false;
    Date notificationDate;

    public AppNotification(User user, String title, String message, String url, String photo, Date notificationDate) {
        this.user = user;
        this.title = title;
        this.message = message;
        this.url = url;
        this.photo = photo;
        this.notificationDate = notificationDate;
    }

    public AppNotification() {

    }
}
