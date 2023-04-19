package com.example.projectdeploy.Community.Report.Controller;

import com.example.projectdeploy.Community.Report.Model.Report;
import com.example.projectdeploy.Community.Report.Service.CrudService;
import com.example.projectdeploy.Community.Report.Service.FetchServiceReport;
import com.example.projectdeploy.Community.Report.dto.CreateReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class ReportController {
    @Autowired
    CrudService crudService;
    @Autowired
    FetchServiceReport fetchService;
    @PostMapping(path="/CreateReport")
    public @ResponseBody
    Report CreateReport(@RequestBody CreateReport report) throws IOException {
        return crudService.CreateReport(report);
    }
    @PatchMapping (path="/UpdateReport")
    public @ResponseBody
    Report UpdateReport(@RequestParam UUID reportId,@RequestParam String reason) throws IOException {
        return crudService.UpdateReport(reportId,reason);
    }
    @DeleteMapping (path="/DeleteReport")
    public @ResponseBody
    String DeleteReport(@RequestParam UUID reportId) throws IOException {
        return crudService.DeleteReport(reportId);
    }
    @GetMapping (path="/MyReports")
    public @ResponseBody
    List<Report> MyReports(@RequestParam UUID userId) {
        return fetchService.MyReports(userId);
    }
    @GetMapping (path="/ReportAgainstMe")
    public @ResponseBody
    List<Report> ReportAgainstMe(@RequestParam UUID userId){
        return fetchService.ReportAgainstMe(userId);
    }
    @GetMapping (path="/Report")
    public @ResponseBody
    Report ReportById(@RequestParam UUID reportId){
        return fetchService.ReportById(reportId);
    }
    @GetMapping (path="/findAllReportForSpecificComment")
    public @ResponseBody
    List<Report> findAllReportForSpecificComment(@RequestParam UUID commentId){
        return fetchService.findAllReportForSpecificComment(commentId);
    }
    @GetMapping (path="/findAllReportForSpecificPost")
    public @ResponseBody
    List<Report> findAllReportForSpecificPost(@RequestParam UUID postId){
        return fetchService.findAllReportForSpecificPost(postId);
    }

}
