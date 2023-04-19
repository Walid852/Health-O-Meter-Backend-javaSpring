package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Repos.MedicineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FetchMedicalService {
    @Autowired
    MedicineRepo medicineRepo;
    @Transactional
    public Medicine findMedicineById(UUID id){
       return medicineRepo.findMedicineById(id);
    }
    @Transactional
    public List<Medicine> findMedicineByNameForMedicalInformationId(UUID id, String name){
        return medicineRepo.findMedicineByNameForMedicalInformationId(id,name);
    }
    @Transactional
    public List<Medicine> findMedicineForMedicalInformationId(UUID id){
        return medicineRepo.findMedicineForMedicalInformationId(id);
    }
    @Transactional
    public List<Medicine> findMedicineForDisease(UUID id){
       return medicineRepo.findMedicineForDisease(id);
    }
    @Transactional
    public List<Medicine> findMedicineByIsNotifiedForMedicalInformationId(UUID id,Boolean isNotified){
        return medicineRepo.findMedicineByIsNotifiedForMedicalInformationId(id,isNotified);
    }

}
