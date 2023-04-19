package com.example.projectdeploy.Community.Like.Controller;


import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Like.Request.LikeRequest;
import com.example.projectdeploy.Community.Like.Service.LikeService;
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
    public @ResponseBody Likee addLike(@RequestBody LikeRequest likeRequest){
        return likeService.addLike(likeRequest);
    }

    @DeleteMapping(value = "/deleteLike")
    public @ResponseBody Likee deleteLike(@RequestBody LikeRequest likeRequest){
        return likeService.deleteLike(likeRequest);
    }

}
