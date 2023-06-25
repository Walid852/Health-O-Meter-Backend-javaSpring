package com.example.projectdeploy.MedicalInformation.Phobia.Service;

import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.Phobia.Repo.PhobiaRepo;
import com.example.projectdeploy.MedicalInformation.Phobia.Request.PhobiaRequest;
import com.example.projectdeploy.MedicalInformation.Phobia.Request.PhobiaUpdate;
import com.example.projectdeploy.MedicalInformation.Phobia.model.Phobia;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PhobiaService {

    @Autowired
    PhobiaRepo phobiaRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;

    @Transactional
    public Response<Phobia> addPhobia(PhobiaRequest phobiaRequest){
        try{
            Phobia phobia=new Phobia();
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(phobiaRequest.getMedicalInformationId());
            if(medicalInformation==null||phobiaRequest.getName()==null) {
                return new Response<>(false, StaticsText.MessageForTest("error", "not have a name"));
            }
            phobia.setName(phobiaRequest.getName());
            phobia.setMedicalInformation(medicalInformation);
            phobia.setCreationDate(phobiaRequest.getDate());
            phobiaRepo.save(phobia);
            List<Phobia> result = new ArrayList<>();
            result.add(phobia);
            return new Response<>(true, StaticsText.MessageForTest("Phobia", "Created"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Phobia>  updatePhobia(PhobiaUpdate phobiaUpdate){
        try{
            Phobia Phobia=phobiaRepo.getPhobiaById(phobiaUpdate.getPhobiaId());
            if(Phobia==null)return new Response<>(false, StaticsText.MessageForTest("error", "not have this Phobia"));
            Phobia.setName(phobiaUpdate.getName());
            Phobia.setCreationDate(phobiaUpdate.getDate());
            phobiaRepo.save(Phobia);
            List<Phobia> result = new ArrayList<>();
            result.add(Phobia);
            return new Response<>(true, StaticsText.MessageForTest("Phobia", "Updated"), result);

        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Phobia>  deletePhobia(UUID id){
        try{
            Phobia deletedPhobia =phobiaRepo.getPhobiaById(id);
            if (deletedPhobia==null)return new Response<>(false, StaticsText.MessageForTest("error", "not have this Phobia"));
            deletedPhobia.setDeleted(true);
            phobiaRepo.save(deletedPhobia);
            List<Phobia> result = new ArrayList<>();
            result.add(deletedPhobia);
            return new Response<>(true, StaticsText.MessageForTest("Phobia", "deleted"), result);

        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Phobia>  getAllPhobia()
    {
        try{
            List<Phobia> result=phobiaRepo.getAllPhobia();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Phobia>  getPhobiaByMedicalInformationId(UUID medicalInformationId){

        try{
            List<Phobia> result=phobiaRepo.getPhobiaByMedicalInformationId(medicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);

        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Phobia>  getPhobiaById(UUID id){
        try{
            List<Phobia> result = null;
            Phobia phobia=phobiaRepo.getPhobiaById(id);
            if(phobia!=null) {
                result.add(phobia);
            }
            else return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);

        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Phobia>  getPhobiaByName(String name)
    {
        try{
            List<Phobia> result=phobiaRepo.getPhobiaByName(name);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);

        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Phobia>  getPhobiaDeletedByMedicalInformationId(UUID medicalInformationId)
    {
        try{
            List<Phobia> result=phobiaRepo.getPhobiaDeletedByMedicalInformationId(medicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);

        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Phobia>  getPhobiaByDate(Date creationDate){

        try{
            List<Phobia> result=phobiaRepo.getPhobiaByDate(creationDate);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Allergies", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Allergies", "Retrieved"),result);

        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }


}
