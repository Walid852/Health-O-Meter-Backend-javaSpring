package com.example.projectdeploy.Community.Comment.Controller;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Comment.Request.CommentRequest;
import com.example.projectdeploy.Community.Comment.Service.CommentService;
import com.example.projectdeploy.Community.Like.Model.Likee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(value = "/addComment")
    public @ResponseBody Comment addComment(@Valid @RequestBody CommentRequest commentRequest){
       return commentService.addComment(commentRequest);
    }

    @DeleteMapping(value = "/deleteComment")
    public @ResponseBody Comment deleteComment(@RequestParam UUID commentId){
        return commentService.deleteComment(commentId);
    }
    @PostMapping(value = "updateComment")
    public @ResponseBody Comment updateComment(@RequestBody CommentRequest commentRequest){
        return commentService.updateComment(commentRequest);
    }
    @PostMapping(value = "/addReply")
    public @ResponseBody Comment addReplyForComment(@RequestBody CommentRequest commentRequest){
        return commentService.addReplyForComment(commentRequest);
    }
    @GetMapping(value = "getReplies")
    public @ResponseBody List<Comment> getRepliesForComment(@RequestParam UUID commentId){
        return commentService.getRepliesForComment(commentId);
    }
    @GetMapping(value = "/getLikes")
    public @ResponseBody List<Likee> getLikesForComments(@RequestParam UUID commentId){
       return commentService.getLikesForComments(commentId);
    }
}
