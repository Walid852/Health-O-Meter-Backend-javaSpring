package com.example.projectdeploy.MedicalInformation.Allergic.Service;

import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.MedicalInformation.Allergic.Repo.AllergyRepo;
import com.example.projectdeploy.MedicalInformation.Allergic.Request.AllergyRequest;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AllergyService {

    @Autowired
    AllergyRepo allergyRepo;

    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    public Allergy addAllergy(AllergyRequest allergyRequest){
        Allergy allergy =new Allergy();
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(allergyRequest.getMedicalInformationId());
        if(medicalInformation==null||allergyRequest.getName()==null)return null;
        allergy.setDate(allergyRequest.getDate());
        allergy.setName(allergyRequest.getName());
        allergy.setMedicalInformation(medicalInformation);
        allergyRepo.save(allergy);
        return allergy;
    }
    @Transactional
    public Allergy updateAllergy(AllergyRequest allergyRequest){
        Allergy updatedAllergy =allergyRepo.getAllergyById(allergyRequest.getAllergyId());
        if(updatedAllergy==null)return null;
        if(allergyRequest.getName()!=null)updatedAllergy.setName(allergyRequest.getName());
        if(allergyRequest.getDate()!=null)updatedAllergy.setDate(allergyRequest.getDate());

        allergyRepo.save(updatedAllergy);
        return updatedAllergy;
    }
    @Transactional
    public String deleteAllergy(UUID id){
        Allergy deletedAllergy =new Allergy();

        if(allergyRepo.findById(id).isPresent()){
            deletedAllergy =allergyRepo.findById(id).get();
        }
        deletedAllergy.setDeleted(true);
        allergyRepo.save(deletedAllergy);
        return "deleted Allergy";
    }
    @Transactional
    public List<Allergy> getAllergyByMedicalInformationId(UUID medicalInformationId){
        return allergyRepo.getAllergyByMedicalInformationId(medicalInformationId);
    }
    @Transactional
    public List<Allergy> getAllergyDeletedByMedicalInformationId(UUID medicalInformationId){
        return allergyRepo.getAllergyDeletedByMedicalInformationId(medicalInformationId);
    }
    @Transactional
    public List<Allergy> getAllAllergy(){
        return allergyRepo.getAllAllergy();
    }
    @Transactional
    public List<Allergy> getAllergyByName(String name){
        return allergyRepo.getAllergyByName(name);
    }
    @Transactional
    public List<Allergy> getAllergyByDate(Date date){
        return allergyRepo.getAllergyByDate(date);
    }
    @Transactional
    public Allergy getAllergyById(UUID id){
        return allergyRepo.getAllergyById(id);
    }
}
