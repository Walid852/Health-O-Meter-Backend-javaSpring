package com.example.projectdeploy.Community.Comment.Service;


import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Comment.Repo.CommentRepo;
import com.example.projectdeploy.Community.Comment.Request.CommentRequest;
import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @Transactional
    public Comment addComment(CommentRequest commentRequest){
        Comment comment=new Comment();
        if(userRepo.findById(commentRequest.getUserId()).isPresent())
            comment.setUser(userRepo.findById(commentRequest.getUserId()).get());
        if(postRepo.findById(commentRequest.getPostId()).isPresent())
            comment.setPost(postRepo.findById(commentRequest.getPostId()).get());
        comment.setComment(commentRequest.getComment());
        commentRepo.save(comment);
        return comment;
    }

    @Transactional
    public Comment deleteComment(UUID commentId){
        Comment deletedComment=new Comment();
        if(commentRepo.findById(commentId).isPresent())
          deletedComment=commentRepo.findById(commentId).get();
        commentRepo.delete(deletedComment);
        return deletedComment;
    }

    @Transactional
    public Comment updateComment(CommentRequest commentRequest){
        Comment updatedComment=new Comment();
        if(commentRepo.findById(commentRequest.getCommentId()).isPresent())
            updatedComment=commentRepo.findById(commentRequest.getCommentId()).get();
        updatedComment.setComment(commentRequest.getComment());
        commentRepo.save(updatedComment);
        return updatedComment;
    }

    @Transactional
    public Comment addReplyForComment(CommentRequest commentRequest){
        Comment reply=new Comment();
        Comment mainComment=new Comment();
        if(commentRepo.findById(commentRequest.getCommentId()).isPresent())
            mainComment=commentRepo.findById(commentRequest.getCommentId()).get();

        reply.setComment(commentRequest.getComment());
        if(userRepo.findById(commentRequest.getUserId()).isPresent())
            reply.setUser(userRepo.findById(commentRequest.getUserId()).get());
       /* if(postRepo.findById(commentRequest.getPostId()).isPresent())
            reply.setPost(postRepo.findById(commentRequest.getPostId()).get());*/

        mainComment.getReplies().add(reply);
        commentRepo.save(reply);
        commentRepo.save(mainComment);
        return mainComment;
    }

    @Transactional
    public List<Comment> getRepliesForComment(UUID commentId){
        Comment comment=new Comment();
        if(commentRepo.findById(commentId).isPresent())
            comment=commentRepo.findById(commentId).get();
        return comment.getReplies();
    }

    @Transactional
    public List<Likee> getLikesForComments(UUID commentId){
        return commentRepo.getLikesForComment(commentId);
    }
}
