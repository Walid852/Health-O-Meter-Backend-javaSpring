package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.Models.Disease;
import com.example.projectdeploy.Disease.Repos.DiseaseRepo;
import com.example.projectdeploy.Disease.Repos.MedicineRepo;
import com.example.projectdeploy.Disease.Repos.MedicineTimeRepo;
import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
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
    public Response<Disease> findDiseaseById(UUID id){
        try {
            List<Disease> result = null;
            Disease disease=diseaseRepo.findDiseaseById(id);
            if(disease!=null) {
                result.add(disease);
            }
            else return new Response<>(false, StaticsText.MessageForTest("Diseases", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Diseases", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Disease>  findAllDiseaseByName(String name){
        try {
            List<Disease> result=diseaseRepo.findAllDiseaseByName(name);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Diseases", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Diseases", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Disease>  findAllDiseaseByIsCured(Boolean isCured){
        try {
            List<Disease> result=diseaseRepo.findAllDiseaseByIsCured(isCured);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Diseases", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Diseases", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Disease>  findDiseaseByName(String name,UUID id){
        try {
            List<Disease> result=diseaseRepo.findDiseaseByName(name,id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Diseases", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Diseases", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Disease>  findDiseaseByIsCured(Boolean isCured,UUID id){
        try {
            List<Disease> result=diseaseRepo.findDiseaseByIsCured(isCured,id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Diseases", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Diseases", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Disease>  findDiseaseByMedicalInformationId(UUID id){
        try {
            List<Disease> result=diseaseRepo.findDiseaseByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Diseases", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Diseases", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    public Response<Disease> filterDiseaseByStartDate(UUID medicalInformationId, Date start, Date end){
        try {
            List<Disease> result= diseaseRepo.filterDiseaseByStartDate(medicalInformationId,start,end);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Diseases", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Diseases", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<Disease>  filterDiseaseByEndDate(UUID medicalInformationId, Date start, Date end){
        try {
            List<Disease> result=diseaseRepo.filterDiseaseByEndDate(medicalInformationId,start,end);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Diseases", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Diseases", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }


}
