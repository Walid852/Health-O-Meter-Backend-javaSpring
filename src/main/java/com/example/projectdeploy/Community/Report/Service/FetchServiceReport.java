package com.example.projectdeploy.Community.Report.Service;

import com.example.projectdeploy.Community.Report.Model.Report;
import com.example.projectdeploy.Community.Report.Repo.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class FetchServiceReport {
    @Autowired
    ReportRepo reportRepo;

    public List<Report> MyReports(UUID userId){
        return reportRepo.findAllReportByFromUserId(userId);
    }
    public List<Report> ReportAgainstMe(UUID userId){
        return reportRepo.findAllReportByToUserId(userId);
    }
    public Report ReportById(UUID reportId){
        return reportRepo.findReportById(reportId);
    }
    public List<Report> findAllReportForSpecificComment(UUID commentId){
        return reportRepo.findAllReportForSpecificComment(commentId);
    }
    public List<Report> findAllReportForSpecificPost(UUID postId){
        return reportRepo.findAllReportForSpecificPost(postId);
    }
}
