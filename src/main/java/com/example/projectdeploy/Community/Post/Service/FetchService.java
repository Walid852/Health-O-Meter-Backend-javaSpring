package com.example.projectdeploy.Community.Post.Service;

import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FetchService {


    @Autowired
    PostRepo postRepo;

    @Transactional
    public List<Post> getPostForUser(UUID userId){
        return postRepo.getUserPost(userId);
    }

    @Transactional
    public List<Post> getPostForCommunity(UUID communityId){
        return postRepo.getCommunityPost(communityId);
    }
}