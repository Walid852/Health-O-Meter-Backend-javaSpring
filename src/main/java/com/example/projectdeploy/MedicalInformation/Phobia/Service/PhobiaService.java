package com.example.projectdeploy.MedicalInformation.Phobia.Service;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.Phobia.Repo.PhobiaRepo;
import com.example.projectdeploy.MedicalInformation.Phobia.Request.PhobiaRequest;
import com.example.projectdeploy.MedicalInformation.Phobia.Request.PhobiaUpdate;
import com.example.projectdeploy.MedicalInformation.Phobia.model.Phobia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PhobiaService {

    @Autowired
    PhobiaRepo phobiaRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;

    @Transactional
    public Phobia addPhobia(PhobiaRequest phobiaRequest){
        Phobia phobia=new Phobia();
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(phobiaRequest.getMedicalInformationId());
        if(medicalInformation==null||phobiaRequest.getName()==null) return null;
        phobia.setName(phobiaRequest.getName());
        phobia.setMedicalInformation(medicalInformation);
        phobiaRepo.save(phobia);
        return phobia;
    }
    @Transactional
    public Phobia updatePhobia(PhobiaUpdate phobiaUpdate){
        Phobia Phobia=phobiaRepo.getPhobiaById(phobiaUpdate.getPhobiaId());
        if(Phobia==null)return null;
        Phobia.setName(phobiaUpdate.getName());
        phobiaRepo.save(Phobia);
        return Phobia;
    }
    @Transactional
    public String deletePhobia(UUID id){
        Phobia deletedPhobia =phobiaRepo.getPhobiaById(id);
        if (deletedPhobia==null)return "not found";
        deletedPhobia.setDeleted(true);
        phobiaRepo.save(deletedPhobia);
        return "phobia deleted";
    }
    @Transactional
    public List<Phobia> getAllPhobia()
    {
        return phobiaRepo.getAllPhobia();
    }
    @Transactional
    public List<Phobia> getPhobiaByMedicalInformationId(UUID medicalInformationId){
        return phobiaRepo.getPhobiaByMedicalInformationId(medicalInformationId);
    }
    @Transactional
    public Phobia getPhobiaById(UUID id){
        return phobiaRepo.getPhobiaById(id);
    }
    @Transactional
    public List<Phobia> getPhobiaByName(String name)
    {
        return phobiaRepo.getPhobiaByName(name);
    }
    @Transactional
    public List<Phobia> getPhobiaDeletedByMedicalInformationId(UUID medicalInformationId)
    {
        return phobiaRepo.getPhobiaDeletedByMedicalInformationId(medicalInformationId);
    }
    @Transactional
    public List<Phobia> getPhobiaByDate(Date creationDate){
        return phobiaRepo.getPhobiaByDate(creationDate);
    }


}
