package com.example.projectdeploy.Community.Report.Repo;

import com.example.projectdeploy.Community.Report.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepo extends JpaRepository<Report, UUID> {
    @Query("select P from Report P where P.id=?1")
    public Report findReportById(UUID id);
    @Query("select P from Report P where P.fromUser.id=?1")
    public List<Report> findAllReportByFromUserId(UUID id);
    @Query("select P from Report P where P.toUser.id=?1")
    public List<Report> findAllReportByToUserId(UUID id);
    @Query("select P from Report P where P.comment.id=?1")
    public List<Report> findAllReportForSpecificComment(UUID id);
    @Query("select P from Report P where P.post.id=?1")
    public List<Report> findAllReportForSpecificPost(UUID id);
}
