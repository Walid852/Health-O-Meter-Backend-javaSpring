package com.example.projectdeploy.Disease.Repos;

import com.example.projectdeploy.Disease.Models.MedicineTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicineTimeRepo extends JpaRepository<MedicineTime, UUID> {
    @Query("select D from MedicineTime D where D.id=?1")
    public MedicineTime findMedicineTimeById(UUID id);
    @Query("select D from MedicineTime D where D.medicine.id=?1")
    public List<MedicineTime> findMedicineTimeByMedicineId(UUID id);
    @Query("select D from MedicineTime D where D.medicine.disease.id=?1")
    public List<MedicineTime> findMedicineTimeByDiseaseId(UUID id);
    @Query("select D from MedicineTime D where D.medicine.disease.medicalInformation.id=?1 and D.medicine.isNotified=true")
    public List<MedicineTime> findMedicineTimeByMedicalInformationIdAndNotified(UUID id);
}
