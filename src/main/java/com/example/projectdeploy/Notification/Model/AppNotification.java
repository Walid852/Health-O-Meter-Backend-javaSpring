package com.example.projectdeploy.Notification.Model;

import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.UUID;

@Entity

@AllArgsConstructor
@Setter
@Getter
public class AppNotification {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User FromUser;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User ToUser;
    private String title;
    private String message;
    private UUID url;
    private TypeUrl typeUrl;
    private String photo;
    @Column(columnDefinition = "boolean default false")
     boolean readd;
    private Date notificationDate;

    public AppNotification(User fromUser, User toUser, String title, String message, UUID url, TypeUrl typeUrl, String photo, Date notificationDate) {

        this.FromUser = fromUser;
        this.ToUser = toUser;
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
