package com.example.projectdeploy.MedicalInformation.Allergic.Repo;

import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AllergyRepo extends JpaRepository<Allergy, UUID> {

    @Query("select P from Allergy P where P.medicalInformation.id=?1 and P.isDeleted=false ")
    List<Allergy> getAllergyByMedicalInformationId(UUID medicalInformationId);

    @Query("select P from Allergy P where P.medicalInformation.id=?1 and P.isDeleted=true ")
    List<Allergy> getAllergyDeletedByMedicalInformationId(UUID medicalInformationId);

    @Query("select P from Allergy P")
    List<Allergy> getAllAllergy();

    @Query("select P from Allergy P where P.name=?1 and P.isDeleted=false ")
    List<Allergy> getAllergyByName(String name);

    @Query("select P from Allergy P where P.date>=?1 and P.isDeleted=false ")
    List<Allergy> getAllergyByDate(Date date);
    @Query("select P from Allergy P where P.id=?1 and P.isDeleted=false ")
    Allergy getAllergyById(UUID id);





}
