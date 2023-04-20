package com.example.projectdeploy.Test.Repos;

import com.example.projectdeploy.Test.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestRepo extends JpaRepository<Test, UUID> {
    /*@Query("select S from Test S where S.id=?1")
    public  Test  TestById(UUID id);
    @Query("select S from  Test S where S.date>=?1")
    public List< Test>  TestByDate(Date date);
    @Query("select S from  Test S ")
    public List< Test> getAllTest();
    @Query("select S from  Test S where S.test=?1")
    public List< Test> getTestsByTypesTest(TypesTest typeTest);
    @Query("select C from  Test C where C.medicalInformation.id=?1 and C.isDeleted=false ")
    public List< Test> renalByMedicalInformationId(UUID medicalInformationId);
    @Query("select C from  Test C where C.medicalInformation.id=?1 and C.isDeleted=true ")
    public List< Test> renalDeletedByMedicalInformationId(UUID medicalInformationId);*/
}
