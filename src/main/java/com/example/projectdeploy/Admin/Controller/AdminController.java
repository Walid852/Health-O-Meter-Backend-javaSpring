package com.example.projectdeploy.Admin.Controller;


import com.example.projectdeploy.Admin.Response.CommentReports;
import com.example.projectdeploy.Admin.Response.PostReports;
import com.example.projectdeploy.Admin.Response.UserReports;
import com.example.projectdeploy.Admin.Service.AdminService;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Request.Pagination;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/getPostReports")
    public @ResponseBody
    Response<PostReports> getPostReports(@RequestBody Pagination pagination){
        return adminService.getPostsReports(pagination);
    }

    @GetMapping(value = "/getCommentReports")
    public @ResponseBody
    Response<CommentReports> getCommentReports(@RequestBody Pagination pagination){
        return adminService.getCommentsReports(pagination);
    }

    @GetMapping(value = "/getUserReports")
    public @ResponseBody
    Response<UserReports> getUserReports(@RequestBody Pagination pagination){
        return adminService.getUsersReports(pagination);
    }


}
