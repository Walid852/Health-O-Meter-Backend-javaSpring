package com.example.projectdeploy.Admin.Service;

import com.example.projectdeploy.Admin.DTO.TakeAction;
import com.example.projectdeploy.Admin.Response.ActionResponse;
import com.example.projectdeploy.Admin.Response.CommentReports;
import com.example.projectdeploy.Admin.Response.PostReports;
import com.example.projectdeploy.Admin.Response.UserReports;
import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Comment.Repo.CommentRepo;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.Community.Post.Request.Pagination;
import com.example.projectdeploy.Community.Report.Model.Report;
import com.example.projectdeploy.Community.Report.Repo.ReportRepo;
import com.example.projectdeploy.Community.Report.dto.ReportAction;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;

import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    ReportRepo reportRepo;
    @Autowired
    PostRepo postRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;

    public Response<PostReports> getPostsReports(){
        //Pageable pageable= PageRequest.of(pagination.getPage(),pagination.getPageSize());
        List<Post> posts=postRepo.getAllPosts();
        List<PostReports> result=new ArrayList<>();
        for(Post post:posts){
            PostReports postReports=new PostReports();
            List<Report> reports=reportRepo.findAllReportForSpecificPost(post.getId());
            if(reports.size()==0)continue;
            List<String> reasons = reports.stream()
                    .map(Report::getReason) // Map each Report object to its id attribute
                    .collect(Collectors.toList());
            postReports.setPostId(post.getId());
            postReports.setDescription(post.getDescription());
            postReports.setPhoto(post.getImage());
            postReports.setReason(reasons);
            result.add(postReports);
        }
        return new Response<>(true, StaticsText.MessageForTest("Reports", "Returned"), result);
    }

    public Response<CommentReports> getCommentsReports(){
        //Pageable pageable= PageRequest.of(pagination.getPage(),pagination.getPageSize());
        List<Comment> comments=commentRepo.getAllComments();
        List<CommentReports> result=new ArrayList<>();
        for(Comment comment:comments){
            CommentReports commentReports=new CommentReports();
            List<Report> reports=reportRepo.findAllReportForSpecificComment(comment.getId());
            if(reports.size()==0)continue;
            List<String> reasons = reports.stream()
                    .map(Report::getReason) // Map each Report object to its id attribute
                    .collect(Collectors.toList());
            commentReports.setCommentId(comment.getId());
            commentReports.setComment(comment.getComment());
            commentReports.setReason(reasons);
            result.add(commentReports);
        }
        return new Response<>(true, StaticsText.MessageForTest("Reports", "Returned"), result);
    }

    public Response<UserReports> getUsersReports(){
        //Pageable pageable= PageRequest.of(pagination.getPage(),pagination.getPageSize());
        List<User> users=userRepo.getReportedUsers();
        List<UserReports> result=new ArrayList<>();
        for(User user :users){
            UserReports userReports=new UserReports();
            List<Report> reports=reportRepo.findAllReportByToUserId(user.getId());
            if(reports.size()==0)continue;
            List<String> reasons = reports.stream()
                    .map(Report::getReason) // Map each Report object to its id attribute
                    .collect(Collectors.toList());
            userReports.setUserId(user.getId());
            userReports.setPhoto(user.getPhoto());
            userReports.setUserName(user.getUserName());
            userReports.setReason(reasons);
            result.add(userReports);
        }
        return new Response<>(true, StaticsText.MessageForTest("Reports", "Returned"), result);
    }

    @Transactional
    public Response<ActionResponse> takeAction(TakeAction takeAction){
        List<ActionResponse> result=new ArrayList<>();
        List<Report> reports=new ArrayList<>();
        System.out.println(takeAction.getPostId());
        if(takeAction.getPostId()!=null){
            reports=reportRepo.findAllReportForSpecificPost(takeAction.getPostId());
            Post post = postRepo.findPostById(takeAction.getPostId());
            if(takeAction.getAction().equals(ReportAction.Deleted)){
                post.setDeleted(true);
                postRepo.save(post);
            }
        }
        else if(takeAction.getCommentId()!=null){
            reports=reportRepo.findAllReportForSpecificComment(takeAction.getCommentId());
                if(takeAction.getAction().equals(ReportAction.Deleted)){
                    commentRepo.deletecomment(takeAction.getCommentId());
            }
        }
        else if(takeAction.getUserId()!=null){
            reports=reportRepo.findAllReportByToUserId(takeAction.getUserId());
            User user=userRepo.findByUserId(takeAction.getUserId());
            if(takeAction.getAction().equals(ReportAction.Deleted)){
                user.setIsSuspended(true);
                userRepo.save(user);
            }
        }else{
            return new Response<>(false, StaticsText.MessageForTest("Request", "is bad"), result);
        }
        List<UUID> reportIds = reports.stream()
                .map(Report::getId) // Map each Report object to its id attribute
                .collect(Collectors.toList());
        reportRepo.updatingAction(takeAction.getAction(),reportIds);
        return new Response<>(true, StaticsText.MessageForTest("Actions", "are updated"), result);
    }

}
