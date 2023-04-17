package com.example.projectdeploy.MedicalInformation.Phobia.Repo;

import com.example.projectdeploy.MedicalInformation.Phobia.model.Phobia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface PhobiaRepo extends JpaRepository<Phobia, UUID> {

    @Query("select P from Phobia P where P.medicalInformation.id=?1 and P.isDeleted=false ")
    List<Phobia> getPhobiaByMedicalInformationId(UUID medicalInformationId);

    @Query("select P from Phobia P ")
    List<Phobia> getAllPhobia();

    @Query("select P from Phobia P where P.id=?1 ")
    Phobia getPhobiaById(UUID id);

    @Query("select P from Phobia P where P.name=?1 and P.isDeleted=false ")
    List<Phobia> getPhobiaByName(String name);

    @Query("select P from Phobia P where P.medicalInformation.id=?1 and P.isDeleted=true ")
    List<Phobia> getPhobiaDeletedByMedicalInformationId(UUID medicalInformationId);
    @Query("select P from Phobia P where P.creationDate>=?1 and P.isDeleted=false ")
    List<Phobia> getPhobiaByDate(Date creationDate);





}
