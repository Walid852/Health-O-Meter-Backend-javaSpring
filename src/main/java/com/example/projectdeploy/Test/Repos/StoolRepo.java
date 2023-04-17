package com.example.projectdeploy.Test.Repos;

import com.example.projectdeploy.Test.Models.Stool;
import com.example.projectdeploy.Test.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface StoolRepo extends JpaRepository<Stool, UUID> {
    @Query("select S from Stool S where S.id=?1")
    public Stool stoolById(UUID id);
    @Query("select C from Stool C where C.medicalInformation.id=?1 and C.isDeleted=false ")
    public List<Test> stoolByMedicalInformationId(UUID medicalInformationId);

    @Query("select C from Stool C where C.medicalInformation.id=?1 and C.isDeleted=true ")
    public List<Test> stoolDeletedByMedicalInformationId(UUID medicalInformationId);
    @Query("select R from Stool R ")
    public List<Test> getAllTestForSpecificType();

    @Query("select R from Stool R where R.date>=?1")
    public List<Test> TestByDate(Date date);
}
