package com.example.projectdeploy.Community.Report.Service;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Comment.Repo.CommentRepo;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.Community.Report.Model.Report;
import com.example.projectdeploy.Community.Report.Repo.ReportRepo;
import com.example.projectdeploy.Community.Report.dto.CreateReport;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
public class CrudService {
    @Autowired
    ReportRepo reportRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;
    @Autowired
    CommentRepo commentRepo;
    @Transactional
    public Report CreateReport(CreateReport report) throws IOException {
        Report reportObject=new Report();
        User user=userRepo.findByUserId(report.fromUserId);
        reportObject.setFromUser(user);
        if(report.postId!=null){
            if(postRepo.findById(report.postId).isPresent()){
            Post post=postRepo.findById(report.postId).get();
            reportObject.setPost(post);
            }
        }
        reportObject.setReason(report.reason);
        if(report.toUserId!=null){
            User user2=userRepo.findByUserId(report.toUserId);
            reportObject.setToUser(user2);
        }
        if(report.commentId!=null){
            if(commentRepo.findById(report.commentId).isPresent()){
            Comment comment=commentRepo.findById(report.commentId).get();
            reportObject.setComment(comment);
            }
        }
        reportRepo.save(reportObject);
        return reportObject;
    }
    public Report UpdateReport(UUID reportId, String reason) throws IOException {
        Report reportObject=reportRepo.findReportById(reportId);
        reportObject.setReason(reason);
        reportRepo.save(reportObject);
        return reportObject;
    }
    public String DeleteReport(UUID reportId) throws IOException {
        Report reportObject=reportRepo.findReportById(reportId);
        reportRepo.delete(reportObject);
        return "Report deleted";
    }
}
