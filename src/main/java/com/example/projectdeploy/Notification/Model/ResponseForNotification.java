package com.example.projectdeploy.Notification.Model;

import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Donate.Model.Donate;
import lombok.Data;

@Data
public class ResponseForNotification {
    public boolean status;
    public String message;
    public Post post;
    public Donate donate;

    public ResponseForNotification(boolean status, String message, Post post, Donate donate) {
        this.status = status;
        this.message = message;
        this.post = post;
        this.donate = donate;
    }
}
