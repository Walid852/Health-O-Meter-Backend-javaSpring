package com.example.projectdeploy.Community.Like.Service;


import com.example.projectdeploy.Community.Comment.Repo.CommentRepo;
import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Like.Repo.LikeRepo;
import com.example.projectdeploy.Community.Like.Request.LikeRequest;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Likee addLike(LikeRequest likeRequest){

        if(!likeBefore(likeRequest)) {
            Likee like = new Likee();
            if (likeRequest.getPostId()!=null) {
                if (postRepo.findById(likeRequest.getPostId()).isPresent())
                    like.setPost(postRepo.findById(likeRequest.getPostId()).get());
            }
            if (likeRequest.getCommentId()!=null) {
                if (commentRepo.findById(likeRequest.getCommentId()).isPresent())
                    like.setComment(commentRepo.findById(likeRequest.getCommentId()).get());
            }
            if (userRepo.findById(likeRequest.getUserId()).isPresent())
                like.setUser(userRepo.findById(likeRequest.getUserId()).get());
            likeRepo.save(like);
            return like;
        }else
            return null;
    }

    @Transactional
    public Likee deleteLike(LikeRequest likeRequest){
        if(likeBefore(likeRequest)) {
            Likee deletedLike = likeRepo.getDeletedLike(likeRequest.getPostId(), likeRequest.getUserId());
            likeRepo.delete(deletedLike);
            return deletedLike;
        }else
            return null;
    }

    @Transactional
    boolean likeBefore(LikeRequest likeRequest){
        int flag=0;
        List<Likee> postLikes=postRepo.getPostLikes(likeRequest.getPostId());

        for(Likee likee:postLikes){
            if(likee.getPost().getId().equals(likeRequest.getPostId()) &&
                    likee.getUser().getId().equals(likeRequest.getUserId())){
                flag=1;
                break;
            }
            if(likee.getComment().getId().equals(likeRequest.getCommentId()) &&
                    likee.getUser().getId().equals(likeRequest.getUserId())){
                flag=1;
                break;
            }
        }
        return flag != 0;
    }

}
