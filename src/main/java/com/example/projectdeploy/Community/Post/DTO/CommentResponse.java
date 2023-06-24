package com.example.projectdeploy.Community.Post.DTO;


import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CommentResponse {

    UUID commentId;
    String content;
    UUID userId;
    String name;
    String photo;
    Boolean showReplies;
}
