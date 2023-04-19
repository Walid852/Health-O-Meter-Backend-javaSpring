package com.example.projectdeploy.Community.Post.Service;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Transactional
    public List<Likee> getMyLike(UUID postId){
        return postRepo.getPostLikes(postId);
    }

    @Transactional
    public List<Comment> getMyComments(UUID postId){
       return postRepo.getPostComments(postId);
    }

}
