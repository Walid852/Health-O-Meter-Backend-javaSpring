package com.example.projectdeploy.Community.Post.Service;

import com.example.projectdeploy.Community.Community;
import com.example.projectdeploy.Community.CommunityRepo;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.Community.Post.Request.NewUpdatePost;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudServices {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CommunityRepo communityRepo;

    @Transactional
    public Post createPost(NewUpdatePost newUpdatePost){
        Post post=new Post();
        //post.setCommunity();
        User user=new User();
        if(userRepo.findById(newUpdatePost.getUid()).isPresent()) {
            user=userRepo.findById(newUpdatePost.getUid()).get();
        }
        if(communityRepo.findById(newUpdatePost.getCommunityId()).isPresent()){
            Community community=communityRepo.findById(newUpdatePost.getCommunityId()).get();
            if(community.getUsers().contains(user)){
                post.setDescription(newUpdatePost.getDescription());
                post.setCommunity(community);
                post.setUser(user);
                post.setFile(newUpdatePost.getFile());
                post.setImage(newUpdatePost.getImage());
                postRepo.save(post);
            }
        }
        return post;
    }

    @Transactional
    public Post updatePost(NewUpdatePost newUpdatePost){
        Post updatedPost=new Post();
        if(postRepo.findById(newUpdatePost.getPostId()).isPresent()){
            updatedPost=postRepo.findById(newUpdatePost.getPostId()).get();
            if(!newUpdatePost.getImage().equals(""))
                updatedPost.setImage(newUpdatePost.getImage());
            if(!newUpdatePost.getDescription().equals(""))
                updatedPost.setDescription(newUpdatePost.getDescription());
            if(!newUpdatePost.getFile().equals(""))
                updatedPost.setFile(newUpdatePost.getFile());
            postRepo.save(updatedPost);

        }
        return updatedPost;
    }

    @Transactional
    public Post deletePost(NewUpdatePost newUpdatePost){
        Post deletedPost =new Post();
        if(postRepo.findById(newUpdatePost.getPostId()).isPresent())
            deletedPost =postRepo.findById(newUpdatePost.getPostId()).get();
        deletedPost.setDeleted(true);
        postRepo.save(deletedPost);
        return deletedPost;
    }



}
