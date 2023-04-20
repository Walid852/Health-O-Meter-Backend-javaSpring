package com.example.projectdeploy.Test.Repos;
import com.example.projectdeploy.Test.Models.CBC;
import com.example.projectdeploy.Test.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface CBCRepo extends JpaRepository<CBC, UUID> {
    @Query("select C from CBC C where C.id=?1")
    public CBC cbcById(UUID id);
    @Query("select C from CBC C where C.medicalInformation.id=?1 and C.isDeleted=false ")
    public List<Test> cbcByMedicalInformationId(UUID medicalInformationId);

    @Query("select C from CBC C where C.medicalInformation.id=?1 and C.isDeleted=true ")
    public List<Test> cbcDeletedByMedicalInformationId(UUID medicalInformationId);
    @Query("select R from CBC R ")
    public List<Test> getAllTestForSpecificType();

    @Query("select R from CBC R where R.date>=?1 and R.date<=?2")
    public List<Test> TestByDate(Date startDate,Date endDate);

}
