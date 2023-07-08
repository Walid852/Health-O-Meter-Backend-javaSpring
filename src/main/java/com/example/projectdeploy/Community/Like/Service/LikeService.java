package com.example.projectdeploy.Community.Like.Service;


import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Comment.Repo.CommentRepo;
import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Like.Repo.LikeRepo;
import com.example.projectdeploy.Community.Like.Request.LikeRequest;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.Notification.Model.NotificationRequest;
import com.example.projectdeploy.Notification.Model.TypeUrl;
import com.example.projectdeploy.Notification.Services.NotificationServices;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    LikeRepo likeRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    NotificationServices notificationServices;

    @Transactional
    public Post addLike(LikeRequest likeRequest){
        if(!likeBefore(likeRequest)) {
            int flag=0;
            Likee like = new Likee();
            Post p=new Post();
            if (likeRequest.getPostId()!=null) {
                if (postRepo.findById(likeRequest.getPostId()).isPresent()) {
                    p=postRepo.findPostById(likeRequest.getPostId());
                    like.setPost(postRepo.findById(likeRequest.getPostId()).get());
                    flag++;
                }
            }
            if (userRepo.findById(likeRequest.getUserId()).isPresent()){
                like.setUser(userRepo.findById(likeRequest.getUserId()).get());
                flag++;
            }
            if(flag==2){
                double noLikes = likeRepo.getNumberLikes(likeRequest.getPostId());
                p.setNumberOfLikes(noLikes+1);
                postRepo.save(p);
                likeRepo.save(like);
                String username=like.getUser().getUserName();
                NotificationRequest notificationRequest=new NotificationRequest(like.getUser().getId(),p.getUser().getId(),
                        "Post like",String.format("%s liked your post",username),p.getId(), TypeUrl.Post,"",
                        Date.valueOf(LocalDate.now()));
                notificationServices.AddNotification(notificationRequest);
                return p;
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You should specify at least post or comment");
            }
        }
        else{
            return deleteLike(likeRequest);
        }

    }

    @Transactional
    public Comment addLikeComment(LikeRequest likeRequest){
        Likee like = new Likee();
        Comment c= new Comment();
        int flag=0;
        if(!likeBefore(likeRequest)) {
            if (likeRequest.getCommentId() != null) {
                if (commentRepo.findById(likeRequest.getCommentId()).isPresent()) {
                    c=commentRepo.findById(likeRequest.getCommentId()).get();
                    like.setComment(commentRepo.findById(likeRequest.getCommentId()).get());
                    flag++;
                }
            }
            if (userRepo.findById(likeRequest.getUserId()).isPresent()) {
                like.setUser(userRepo.findById(likeRequest.getUserId()).get());
                flag++;
            }
            if (flag == 2) {
                double noLikes = likeRepo.getNOLikeOnComment(likeRequest.getCommentId());
                c.setNumberOfLikes(noLikes + 1);
                commentRepo.save(c);
                String username=like.getUser().getUserName();
                NotificationRequest notificationRequest=new NotificationRequest(like.getUser().getId(),c.getUser().getId(),
                        "Comment like",String.format("%s liked your comment",username),like.getComment().getPost().getId(), TypeUrl.Post,"",
                        Date.valueOf(LocalDate.now()));
                notificationServices.AddNotification(notificationRequest);
            }
            return c;
        }
        else{
            return deleteLikeForComment(likeRequest);
        }
    }

    @Transactional
    public Comment deleteLikeForComment(LikeRequest likeRequest){
        Comment c=new Comment();
        Likee deletedLike = likeRepo.getDeletedLikeForComment(likeRequest.getCommentId(), likeRequest.getUserId());
        likeRepo.deleteLike(deletedLike.getId());
        if(likeRequest.getCommentId()!=null) {
            double noLikes = likeRepo.getNOLikeOnComment(likeRequest.getCommentId());
            if(commentRepo.findById(likeRequest.getCommentId()).isPresent()) {
                c = commentRepo.findById(likeRequest.getCommentId()).get();
                c.setNumberOfLikes(noLikes);
                commentRepo.save(c);
                return c;
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "comment is not found");
            }
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you should send comment");
    }

    @Transactional
    public Post deleteLike(LikeRequest likeRequest){
        Post p=new Post();
        Likee deletedLike = likeRepo.getDeletedLike(likeRequest.getPostId(), likeRequest.getUserId());
        likeRepo.deleteLike(deletedLike.getId());
            if(likeRequest.getPostId()!=null) {
                double noLikes = likeRepo.getNumberLikes(likeRequest.getPostId());
                p = postRepo.findPostById(likeRequest.getPostId());
                p.setNumberOfLikes(noLikes);
                postRepo.save(p);
                return p;
        }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post is not found");
    }

    @Transactional
    boolean likeBefore(LikeRequest likeRequest){
        int flag=0;
        if(likeRequest.getCommentId()!=null) {
            List<Likee> commentLikes=commentRepo.getLikesForComment(likeRequest.getCommentId());
            for(Likee likee:commentLikes) {
                if (likee.getComment().getId().equals(likeRequest.getCommentId()) &&
                        likee.getUser().getId().equals(likeRequest.getUserId())) {
                    flag = 1;
                    break;
                }
            }
        }
        if(likeRequest.getPostId()!=null) {
            List<Likee> postLikes=postRepo.getPostLikes(likeRequest.getPostId());
            for (Likee likee : postLikes) {
                if (likee.getPost().getId().equals(likeRequest.getPostId()) &&
                        likee.getUser().getId().equals(likeRequest.getUserId())) {
                    flag = 1;
                    break;
                }

            }
        }
        return flag != 0;
    }

}
