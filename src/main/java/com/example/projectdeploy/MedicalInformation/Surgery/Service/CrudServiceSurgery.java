package com.example.projectdeploy.MedicalInformation.Surgery.Service;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.Surgery.Model.Surgery;
import com.example.projectdeploy.MedicalInformation.Surgery.Repo.SurgeryRepo;
import com.example.projectdeploy.MedicalInformation.Surgery.dto.CreateSurgery;
import com.example.projectdeploy.MedicalInformation.Surgery.dto.UpdateSurgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CrudServiceSurgery {
    @Autowired
    SurgeryRepo surgeryRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    public Surgery AddSurgery(CreateSurgery createSurgery){
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(createSurgery.getMedicalInformationId());
        if (medicalInformation==null||createSurgery.getBodyMember()==null||createSurgery.getName()==null) return null;
        Surgery surgery=new Surgery();
        surgery.setMedicalInformation(medicalInformation);
        surgery.setBodyMember(createSurgery.getBodyMember());
        //surgery.setSurgeryDate(createSurgery.getSurgeryDate());
        surgery.setName(createSurgery.getName());
        surgeryRepo.save(surgery);
        return surgery;
    }
    @Transactional
    public Surgery UpdateSurgery(UpdateSurgery updateSurgery){
        Surgery surgery=surgeryRepo.findSurgeryById(updateSurgery.getId());
        if (surgery==null) return null;
        if(updateSurgery.getBodyMember()!=null)surgery.setBodyMember(updateSurgery.getBodyMember());
        //surgery.setSurgeryDate(updateSurgery.getSurgeryDate());
        if(updateSurgery.getName()!=null)surgery.setName(updateSurgery.getName());
        surgeryRepo.save(surgery);
        return surgery;
    }
    @Transactional
    public String DeleteSurgery(UUID surgeryId){
        Surgery surgery=surgeryRepo.findSurgeryById(surgeryId);
        if (surgery==null) return null;
        surgery.setDeleted(true);
        surgeryRepo.save(surgery);
        return "Deleted Surgery";
    }
}
