package com.example.projectdeploy.Community.Post.Request;

import lombok.Data;

import java.util.UUID;

@Data
public class NewUpdatePost {
    String description;
    UUID uid;
    String image;
    String file;
    UUID communityId;
    UUID postId;

}
