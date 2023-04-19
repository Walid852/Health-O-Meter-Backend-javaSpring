package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.Models.Disease;
import com.example.projectdeploy.Disease.Repos.DiseaseRepo;
import com.example.projectdeploy.Disease.Repos.MedicineRepo;
import com.example.projectdeploy.Disease.Repos.MedicineTimeRepo;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FetchDiseasesService {
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Autowired
    DiseaseRepo diseaseRepo;
    @Autowired
    MedicineRepo medicineRepo;
    @Autowired
    MedicineTimeRepo medicineTimeRepo;
    @Transactional
    public Disease findDiseaseById(UUID id){
        return diseaseRepo.findDiseaseById(id);
    }
    @Transactional
    public List<Disease> findAllDiseaseByName(String name){
        return diseaseRepo.findAllDiseaseByName(name);
    }
    @Transactional
    public List<Disease> findAllDiseaseByIsCured(Boolean isCured){
        return diseaseRepo.findAllDiseaseByIsCured(isCured);
    }
    @Transactional
    public List<Disease> findDiseaseByName(String name,UUID id){
        return diseaseRepo.findDiseaseByName(name,id);
    }
    @Transactional
    public List<Disease> findDiseaseByIsCured(Boolean isCured,UUID id){
        return diseaseRepo.findDiseaseByIsCured(isCured,id);
    }
    @Transactional
    public List<Disease> findDiseaseByMedicalInformationId(UUID id){
        return diseaseRepo.findDiseaseByMedicalInformationId(id);
    }
    @Transactional
    public List<Disease> filterDiseaseByStartDate(UUID medicalInformationId, Date start, Date end){
        return diseaseRepo.filterDiseaseByStartDate(medicalInformationId,start,end);
    }
    @Transactional
    public List<Disease> filterDiseaseByEndDate(UUID medicalInformationId, Date start, Date end){
        return diseaseRepo.filterDiseaseByEndDate(medicalInformationId,start,end);
    }


}
