package com.example.projectdeploy.Notification.EventListener;

import com.example.projectdeploy.Notification.Model.AppNotification;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CommunityCreatedEvent {

    private AppNotification appNotification;
}
