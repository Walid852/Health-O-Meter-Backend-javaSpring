package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.Test.Models.Renal;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Repos.RenalRepo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RenalTest implements BaseTest{
    @Autowired
    RenalRepo renalRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;

    @Transactional
    @Override
    public Response<Test> AddTest(Request test) {
        try {
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
            if(medicalInformation==null)return new Response<>(false, StaticsText.MessageForTest("renal Test", "not Found"));
            Renal renal=new Renal(test.getUrea(),test.getCreatinineInSerum(),
                    test.getUricAcid(),medicalInformation,test.getIsDeleted(),test.getDate(),
                    test.getTest());
            renalRepo.saveAndFlush(renal);
            List<Test> result = new ArrayList<>();
            result.add(renal);
            return new Response<>(true, StaticsText.MessageForTest("renal Test", "Created"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    @Override
    public Response<Test> UpdateTest(Request test) {
        try {
            Renal renal= renalRepo.renalById(test.getId());
            if(renal==null)return new Response<>(false, StaticsText.MessageForTest("renal Test", "not Found"));
            if(test.getUrea()!=-1)renal.setUrea(test.getUrea());
            if(test.getCreatinineInSerum()!=-1)renal.setCreatinineInSerum(test.getCreatinineInSerum());
            if(test.getUricAcid()!=-1)renal.setUricAcid(test.getUricAcid());
            renalRepo.save(renal);
            List<Test> result = new ArrayList<>();
            result.add(renal);
            return new Response<>(true, StaticsText.MessageForTest("renal Test", "Updated"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    @Override
    public Response<Test> DeleteTest(UUID id) {
        try {
            Renal renal= renalRepo.renalById(id);
            if(renal==null)return new Response<>(false, StaticsText.MessageForTest("renal Test", "not Found"));
            renal.setIsDeleted(true);
            renalRepo.save(renal);
            List<Test> result = new ArrayList<>();
            result.add(renal);
            return new Response<>(true, StaticsText.MessageForTest("renal Test", "deleted"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> RetrieveAllTest() {
        try{
            List<Test> result=renalRepo.getAllTestForSpecificType();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("renal Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("renal Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    @Override
    public Response<Test> RetrieveAllTestByMedicalInformationId(UUID id) {
        try{
            List<Test> result=renalRepo.renalByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("renal Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("renal Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> testDeletedByMedicalInformationId(UUID id) {
        try{
            List<Test> result=renalRepo.renalDeletedByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("renal Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("renal Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> TestByDate(Date startDate,Date endDate) {
        try{
            List<Test> result=renalRepo.TestByDate(startDate,endDate);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("renal Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("renal Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Transactional
    @Override
    public TypesTest GetType() {
        return TypesTest.Renal;
    }
}
