package com.example.projectdeploy.Test.Repos;

import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.Urine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface UrineRpo extends JpaRepository<Urine, UUID> {
    @Query("select S from Urine S where S.id=?1")
    public Urine UrineById(UUID id);
    @Query("select C from Urine C where C.medicalInformation.id=?1 and C.isDeleted=false ")
    public List<Test> urineByMedicalInformationId(UUID medicalInformationId);
    @Query("select C from Urine C where C.medicalInformation.id=?1 and C.isDeleted=true ")
    public List<Test> urineDeletedByMedicalInformationId(UUID medicalInformationId);
    @Query("select R from Urine R ")
    public List<Test> getAllTestForSpecificType();
    @Query("select R from Urine R where R.date>=?1 and R.date<=?2")
    public List<Test> TestByDate(Date startDate,Date endDate);
}
