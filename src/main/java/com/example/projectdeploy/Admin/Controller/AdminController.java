package com.example.projectdeploy.Admin.Controller;


import com.example.projectdeploy.Admin.DTO.TakeAction;
import com.example.projectdeploy.Admin.Response.ActionResponse;
import com.example.projectdeploy.Admin.Response.CommentReports;
import com.example.projectdeploy.Admin.Response.PostReports;
import com.example.projectdeploy.Admin.Response.UserReports;
import com.example.projectdeploy.Admin.Service.AdminService;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Request.Pagination;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/getPostReports")
    public @ResponseBody
    Response<PostReports> getPostReports(){
        return adminService.getPostsReports();
    }

    @GetMapping(value = "/getCommentReports")
    public @ResponseBody
    Response<CommentReports> getCommentReports(){
        return adminService.getCommentsReports();
    }

    @GetMapping(value = "/getUserReports")
    public @ResponseBody
    Response<UserReports> getUserReports(){
        return adminService.getUsersReports();
    }
    @PostMapping(value = "/takeAction")
    public @ResponseBody
    Response<ActionResponse> takeAction(@RequestBody TakeAction takeAction){
        return adminService.takeAction(takeAction);
    }


}
