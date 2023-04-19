package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.Models.MedicineTime;
import com.example.projectdeploy.Disease.Repos.MedicineTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FetchMedicalTimesService {
    @Autowired
    MedicineTimeRepo medicineTimeRepo;
    public MedicineTime findMedicineTimeById(UUID id){
        return medicineTimeRepo.findMedicineTimeById(id);
    }
    public List<MedicineTime> findMedicineTimeByMedicineId(UUID id){
        return medicineTimeRepo.findMedicineTimeByMedicineId(id);
    }
    public List<MedicineTime> findMedicineTimeByDiseaseId(UUID id){
        return medicineTimeRepo.findMedicineTimeByDiseaseId(id);
    }
    public List<MedicineTime> findMedicineTimeByMedicalInformationIdAndNotified(UUID id){
        return medicineTimeRepo.findMedicineTimeByMedicalInformationIdAndNotified(id);
    }
}
