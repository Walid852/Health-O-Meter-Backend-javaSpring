package com.example.projectdeploy.Community.Post.Controller;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Request.NewUpdatePost;
import com.example.projectdeploy.Community.Post.Request.Pagination;
import com.example.projectdeploy.Community.Post.Service.CrudServices;
import com.example.projectdeploy.Community.Post.Service.FetchService;
import com.example.projectdeploy.Community.Post.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class PostController {

    @Autowired
    CrudServices crudServices;

    @Autowired
    PostService postService;

    @Autowired
    FetchService fetchService;

    @PostMapping(value = "/createPost")
    public @ResponseBody Post createPost(@RequestBody NewUpdatePost newUpdatePost){
        return crudServices.createPost(newUpdatePost);
    }

    @PutMapping(value = "/updatePost")
    public @ResponseBody Post updatePost(@RequestBody NewUpdatePost newUpdatePost){
        return crudServices.updatePost(newUpdatePost);
    }

    @PutMapping(value = "/deletePost")
    public @ResponseBody Post deletePost(@RequestBody NewUpdatePost newUpdatePost){
        return crudServices.deletePost(newUpdatePost);
    }

    @GetMapping(value = "/getPostLikes")
    public @ResponseBody List<Likee> getMyLike(@RequestParam UUID postId){
        return postService.getMyLike(postId);
    }

    @GetMapping(value = "/getPostComments")
    public @ResponseBody List<Comment> getMyComment(@RequestParam UUID postId){
        return postService.getMyComments(postId);
    }

    @GetMapping(value = "/getUserPosts")
    public @ResponseBody List<Post> getPostForUser(@RequestBody Pagination pagination){
        return fetchService.getPostForUser(pagination);
    }

    @PostMapping(value = "/getCommunityPosts")
    public @ResponseBody List<Post> getPostForCommunity(@RequestBody Pagination pagination){
        return fetchService.getPostForCommunity(pagination);
    }
}
