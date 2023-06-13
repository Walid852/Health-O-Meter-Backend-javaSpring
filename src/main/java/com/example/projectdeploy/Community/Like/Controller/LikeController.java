package com.example.projectdeploy.Community.Like.Controller;


import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Like.Request.LikeRequest;
import com.example.projectdeploy.Community.Like.Service.LikeService;
import com.example.projectdeploy.Community.Post.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LikeController {

    @Autowired
    LikeService likeService;

    @PostMapping(value = "/addLike")
    public @ResponseBody Post addLike(@RequestBody LikeRequest likeRequest){
        return likeService.addLike(likeRequest);
    }

    @PostMapping(value = "/addLikeComment")
    public @ResponseBody
    Comment addLikeForComment(@RequestBody LikeRequest likeRequest){
        return likeService.addLikeComment(likeRequest);
    }

    @DeleteMapping(value = "/deleteLike")
    public @ResponseBody Post deleteLike(@RequestBody LikeRequest likeRequest){
        return likeService.deleteLike(likeRequest);
    }

}
