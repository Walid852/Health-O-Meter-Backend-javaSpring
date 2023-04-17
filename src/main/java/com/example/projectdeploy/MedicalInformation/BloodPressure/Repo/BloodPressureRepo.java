package com.example.projectdeploy.MedicalInformation.BloodPressure.Repo;

import com.example.projectdeploy.MedicalInformation.BloodPressure.Model.BloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.BloodPressureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BloodPressureRepo extends JpaRepository<BloodPressure,UUID> {
    @Query("select B from BloodPressure B where B.id=?1")
    public BloodPressure findBloodPressureById(UUID id);
    @Query("select B from BloodPressure B where B.medicalInformation.id=?1 and B.isDeleted=false ")
    public List<BloodPressure> findBloodPressureByMedicalInformationId(UUID id);
    @Query("select B from BloodPressure B where B.bloodPressureCategory=?1 and B.isDeleted=false ")
    public List<BloodPressure> findBloodPressureByBloodPressureCategory(BloodPressureCategory bloodPressureCategory);
    @Query("select B from BloodPressure B where B.systolic>=?1 and B.isDeleted=false ")
    public List<BloodPressure> findBloodPressureBySystolic(int systolic);
    @Query("select B from BloodPressure B where B.diastolic>=?1 and B.isDeleted=false ")
    public List<BloodPressure> findBloodPressureByDiastolic(int diastolic);
    @Query("select B from BloodPressure B where  B.medicalInformation.id=?1 and B.isDeleted=true")
    public List<BloodPressure> findBloodPressureDeleted(UUID id);
    @Query("select B from BloodPressure B ")
    public List<BloodPressure> findAllBloodPressure();
    @Query("select B from BloodPressure B where B.date>=?1 and B.date<=?2")
    public List<BloodPressure> findAllBloodPressureByDate(Date start,Date endDate);

}
