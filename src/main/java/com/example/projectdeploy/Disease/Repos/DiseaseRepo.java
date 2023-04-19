package com.example.projectdeploy.Disease.Repos;

import com.example.projectdeploy.Disease.Models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, UUID> {
    @Query("select D from Disease D where D.id=?1")
    public Disease findDiseaseById(UUID id);
    @Query("select D from Disease D where D.name=?1")
    public List<Disease> findAllDiseaseByName(String name);
    @Query("select D from Disease D where D.isCured=?1")
    public List<Disease> findAllDiseaseByIsCured(Boolean isCured);
    @Query("select D from Disease D where D.name=?1 and D.medicalInformation.id=?2 and D.isDeleted=false")
    public List<Disease> findDiseaseByName(String name,UUID id);
    @Query("select D from Disease D where D.isCured=?1and D.medicalInformation.id=?2 and D.isDeleted=false")
    public List<Disease> findDiseaseByIsCured(Boolean isCured,UUID id);
    @Query("select D from Disease D where D.medicalInformation.id=?1 and D.isDeleted=false order by D.startDate desc")
    public List<Disease> findDiseaseByMedicalInformationId(UUID id);
    @Query("select D from Disease D where D.medicalInformation.id=?1 and D.isDeleted=false  and D.startDate between ?2 and ?3")
    List<Disease> filterDiseaseByStartDate(UUID medicalInformationId, Date start, Date end);
    @Query("select D from Disease D where D.medicalInformation.id=?1 and D.isDeleted=false  and D.endDate between ?2 and ?3")
    List<Disease> filterDiseaseByEndDate(UUID medicalInformationId, Date start, Date end);
}
