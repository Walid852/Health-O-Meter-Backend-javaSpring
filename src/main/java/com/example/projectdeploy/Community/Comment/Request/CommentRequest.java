package com.example.projectdeploy.Community.Comment.Request;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentRequest {
    UUID userId;
    String Comment;
    UUID postId;
    UUID repliedCommentId;
    UUID commentId;
}
