package com.example.projectdeploy.Disease.Repos;

import com.example.projectdeploy.Disease.Models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicineRepo  extends JpaRepository<Medicine, UUID> {
    @Query("select D from Medicine D where D.id=?1")
    public Medicine findMedicineById(UUID id);
    @Query("select D from Medicine D where D.disease.medicalInformation.id=?1 and D.name=?1")
    public List<Medicine> findMedicineByNameForMedicalInformationId(UUID id,String name);
    @Query("select D from Medicine D where D.disease.medicalInformation.id=?1")
    public List<Medicine> findMedicineForMedicalInformationId(UUID id);
    @Query("select D from Medicine D where D.disease.id=?1")
    public List<Medicine> findMedicineForDisease(UUID id);
    @Query("select D from Medicine D where D.disease.medicalInformation.id=?1 and D.isNotified=?1")
    public List<Medicine> findMedicineByIsNotifiedForMedicalInformationId(UUID id,Boolean isNotified);
}
