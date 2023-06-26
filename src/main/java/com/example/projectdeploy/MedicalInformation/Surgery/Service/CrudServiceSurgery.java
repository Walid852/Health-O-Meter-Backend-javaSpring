package com.example.projectdeploy.MedicalInformation.Surgery.Service;

import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.Surgery.Model.Surgery;
import com.example.projectdeploy.MedicalInformation.Surgery.Repo.SurgeryRepo;
import com.example.projectdeploy.MedicalInformation.Surgery.dto.CreateSurgery;
import com.example.projectdeploy.MedicalInformation.Surgery.dto.UpdateSurgery;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CrudServiceSurgery {
    @Autowired
    SurgeryRepo surgeryRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    public Response<Surgery>  AddSurgery(CreateSurgery createSurgery){
        try {
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(createSurgery.getMedicalInformationId());
            if (medicalInformation==null||createSurgery.getBodyMember()==null||createSurgery.getName()==null) {
                return new Response<>(false, StaticsText.MessageForTest("error", "not have a name"));
            }
            Surgery surgery=new Surgery();
            surgery.setMedicalInformation(medicalInformation);
            surgery.setBodyMember(createSurgery.getBodyMember());
            surgery.setCreationDate(createSurgery.getDate());
            surgery.setName(createSurgery.getName());
            surgeryRepo.save(surgery);
            List<Surgery> result = new ArrayList<>();
            result.add(surgery);
            return new Response<>(true, StaticsText.MessageForTest("Surgery", "Created"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Surgery> UpdateSurgery(UpdateSurgery updateSurgery){

        try {
            Surgery surgery=surgeryRepo.findSurgeryById(updateSurgery.getId());
            if (surgery==null) return new Response<>(false, StaticsText.MessageForTest("error", "not have this Surgery"));
            if(updateSurgery.getBodyMember()!=null)surgery.setBodyMember(updateSurgery.getBodyMember());
            //surgery.setSurgeryDate(updateSurgery.getSurgeryDate());
            if(updateSurgery.getName()!=null)surgery.setName(updateSurgery.getName());
            if(updateSurgery.getDate()!=null)
                surgery.setCreationDate(updateSurgery.getDate());
            surgeryRepo.save(surgery);
            List<Surgery> result = new ArrayList<>();
            result.add(surgery);
            return new Response<>(true, StaticsText.MessageForTest("Surgery", ""), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Surgery> DeleteSurgery(UUID surgeryId){
        try {
            Surgery surgery=surgeryRepo.findSurgeryById(surgeryId);
            if (surgery==null) return new Response<>(false, StaticsText.MessageForTest("error", "not have this Surgery"));
            surgery.setDeleted(true);
            surgeryRepo.save(surgery);
            List<Surgery> result = new ArrayList<>();
            result.add(surgery);
            return new Response<>(true, StaticsText.MessageForTest("Surgery", ""), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
}
