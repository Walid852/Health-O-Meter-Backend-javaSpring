package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Repo;


import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model.SugarBloodTest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface SugarBloodTestRepo extends JpaRepository<SugarBloodTest, UUID> {

    @Query("select O from SugarBloodTest O where O.medicalInformation.id=?1 and O.isDeleted=false ")
    List<SugarBloodTest> getSugarBloodTestByMedicalInformationId(UUID medicalInformationId);
    @Query("select O from SugarBloodTest O where O.medicalInformation.id=?1 and O.isDeleted=true ")
    List<SugarBloodTest> getDeletedSugarBloodTestByMedicalInformationId(UUID medicalInformationId);
    @Query("select O from SugarBloodTest O ")
    List<SugarBloodTest> getAllSugarBloodTest();
    @Query("select O from SugarBloodTest O where O.id=?1")
    SugarBloodTest getSugarBloodTestById(UUID id);
    @Query("select O from SugarBloodTest O where O.testPeriod=?1")
    List<SugarBloodTest> getSugarBloodTestByTestPeriod(TestPeriod testPeriod);
    @Query("select O from SugarBloodTest O where O.readd<=?1")
    List<SugarBloodTest> getSugarBloodTestByRead(int read);
    @Query("select O from SugarBloodTest O where O.medicalInformation.id=?1 and O.isDeleted=false  and O.date between ?2 and ?3")
    List<SugarBloodTest> filterTestByDateUser(UUID medicalInformationId, Date start,Date end);



}
