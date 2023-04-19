package com.example.projectdeploy.Community.Like.Request;

import lombok.Data;

import java.util.UUID;

@Data
public class LikeRequest {
    UUID userId;
    UUID postId;
    UUID commentId;
}
