package com.example.projectdeploy.Community.Report.Repo;

import com.example.projectdeploy.Community.Report.Model.Report;
import com.example.projectdeploy.Community.Report.dto.ReportAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepo extends JpaRepository<Report, UUID> {
    @Query("select P from Report P where P.id=?1")
     Report findReportById(UUID id);
    @Query("select P from Report P where P.fromUser.id=?1")
     List<Report> findAllReportByFromUserId(UUID id);
    @Query("select P from Report P where P.toUser.id=?1 and P.action='Pending'")
     List<Report> findAllReportByToUserId(UUID id);
    @Query("select P from Report P where P.comment.id=?1 and P.action='Pending'")
     List<Report> findAllReportForSpecificComment(UUID id);
    @Query("select P from Report P where P.post.id=?1 and P.action='Pending'")
     List<Report> findAllReportForSpecificPost(UUID id);
    @Modifying
    @Query("UPDATE Report r SET r.action = ?1 WHERE r.id IN ?2")
    void updatingAction(ReportAction action,List<UUID> reportsIds);


}
