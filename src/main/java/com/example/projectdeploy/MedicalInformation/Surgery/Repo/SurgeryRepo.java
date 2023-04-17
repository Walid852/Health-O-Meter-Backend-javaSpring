package com.example.projectdeploy.MedicalInformation.Surgery.Repo;

import com.example.projectdeploy.MedicalInformation.Surgery.Model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface SurgeryRepo extends JpaRepository<Surgery, UUID> {
    @Query("select S from Surgery S where S.id=?1")
    public Surgery findSurgeryById(UUID id);
    @Query("select S from Surgery S")
    public List<Surgery> findAllSurgery();
    @Query("select S from Surgery S where S.medicalInformation.id=?1 and S.isDeleted=false ")
    public List<Surgery> findSurgeryByMedicalInformationId(UUID id);
    @Query("select S from Surgery S where S.medicalInformation.id=?1 and S.isDeleted=true")
    public List<Surgery> findSurgeryDeleted(UUID id);
    @Query("select S from Surgery S where S.name=?1 and S.isDeleted=false")
    public List<Surgery> findSurgeryByName(String name);
    @Query("select S from Surgery S where S.bodyMember=?1 and S.isDeleted=false")
    public List<Surgery> findSurgeryByBodyMember(String bodyMember);
    /*@Query("select S from Surgery S where S.surgeryDate=?1 and S.medicalInformation.id=?2")
    public List<Surgery> findSurgeryBySurgeryDate(Date surgeryDate, UUID medicalInformationId);*/
    @Query("select S from Surgery S where S.creationDate=?1 and S.medicalInformation.id=?2")
    public List<Surgery> findSurgeryByCreationDate(Date creationDate,UUID medicalInformationId);

}
