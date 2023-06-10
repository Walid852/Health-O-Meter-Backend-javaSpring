package com.example.projectdeploy.MedicalInformation.Surgery.Service;

import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.Phobia.model.Phobia;
import com.example.projectdeploy.MedicalInformation.Surgery.Model.Surgery;
import com.example.projectdeploy.MedicalInformation.Surgery.Repo.SurgeryRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
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
    public Response<Surgery> findSurgeryById(UUID id){
        try {
            List<Surgery> result = null;
            Surgery surgery=surgeryRepo.findSurgeryById(id);
            if(surgery!=null) {
                result.add(surgery);
            }
            else return new Response<>(false, StaticsText.MessageForTest("Surgeries", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Surgeries", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Surgery> findAllSurgery(){
        try {

            List<Surgery> result=surgeryRepo.findAllSurgery();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Surgeries", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Surgery> findSurgeryByMedicalInformationId(UUID id){

        try {

            List<Surgery> result=surgeryRepo.findSurgeryByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Surgeries", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Surgery> findSurgeryDeleted(UUID id){
        try {

            List<Surgery> result=surgeryRepo.findSurgeryDeleted(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Surgeries", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Surgery> findSurgeryByName(String name){
        try {

            List<Surgery> result=surgeryRepo.findSurgeryByName(name);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Surgeries", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Surgery> findSurgeryByBodyMember(String bodyMember){
        try {
            List<Surgery> result=surgeryRepo.findSurgeryByBodyMember(bodyMember);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Surgeries", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Surgery> findSurgeryByCreationDate(Date creationDate,UUID medicalInformationId){
        try {

            List<Surgery> result= surgeryRepo.findSurgeryByCreationDate(creationDate,medicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Surgeries", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    /*@Transactional
    public List<Surgery> findSurgeryBySurgeryDate(Date surgeryDate, UUID medicalInformationId){
        return surgeryRepo.findSurgeryBySurgeryDate(surgeryDate,medicalInformationId);
    }*/
}
