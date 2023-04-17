package com.example.projectdeploy.MedicalInformation.Surgery.Service;

import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.Surgery.Model.Surgery;
import com.example.projectdeploy.MedicalInformation.Surgery.Repo.SurgeryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FetchServiceSurgery {
    @Autowired
    SurgeryRepo surgeryRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    public Surgery findSurgeryById(UUID id){
        return surgeryRepo.findSurgeryById(id);
    }
    @Transactional
    public List<Surgery> findAllSurgery(){
        return surgeryRepo.findAllSurgery();
    }
    @Transactional
    public List<Surgery> findSurgeryByMedicalInformationId(UUID id){
        return surgeryRepo.findSurgeryByMedicalInformationId(id);
    }
    @Transactional
    public List<Surgery> findSurgeryDeleted(UUID id){
        return surgeryRepo.findSurgeryDeleted(id);
    }
    @Transactional
    public List<Surgery> findSurgeryByName(String name){
        return surgeryRepo.findSurgeryByName(name);
    }
    @Transactional
    public List<Surgery> findSurgeryByBodyMember(String bodyMember){
        return surgeryRepo.findSurgeryByBodyMember(bodyMember);
    }
    @Transactional
    public List<Surgery> findSurgeryByCreationDate(Date creationDate,UUID medicalInformationId){
        return surgeryRepo.findSurgeryByCreationDate(creationDate,medicalInformationId);
    }
    /*@Transactional
    public List<Surgery> findSurgeryBySurgeryDate(Date surgeryDate, UUID medicalInformationId){
        return surgeryRepo.findSurgeryBySurgeryDate(surgeryDate,medicalInformationId);
    }*/
}
