package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.Test.Models.Stool;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Repos.StoolRepo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class StoolTest implements BaseTest{
    @Autowired
    StoolRepo stoolRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Override
    public Response<Test> AddTest(Request test) {
        try{
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
            if(medicalInformation==null)return new Response<>(false, StaticsText.MessageForTest("stool Test", "not Found"));
            Stool stool=new Stool(medicalInformation, test.getIsDeleted(),test.getDate()
                    ,test.getTest(),test.getColor(),test.getConsistency(),test.getFoodParticles()
                    ,test.getMucus(),test.getBlood(),test.getStarch(),test.getMusclefibers()
                    ,test.getVegetables(),test.getProtozoa(),test.getCiliates()
            );
            stoolRepo.saveAndFlush(stool);
            List<Test> result = new ArrayList<>();
            result.add(stool);
            return new Response<>(true, StaticsText.MessageForTest("stool Test", "Created"), result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }

    @Override
    public Response<Test> UpdateTest(Request test) {
        try{
            Stool stool=stoolRepo.stoolById(test.getId());
            if(stool==null)return new Response<>(false, StaticsText.MessageForTest("stool Test", "not Found"));
            if(test.getBlood()!=null)stool.setBlood(test.getBlood());
            if(test.getCiliates()!=null)stool.setCiliates(test.getCiliates());
            if(test.getColor()!=null)stool.setColor(test.getColor());
            if(test.getConsistency()!=null)stool.setConsistency(test.getConsistency());
            if(test.getFoodParticles()!=null)stool.setFoodParticles(test.getFoodParticles());
            if(test.getMucus()!=null)stool.setMucus(test.getMucus());
            if(test.getProtozoa()!=null)stool.setProtozoa(test.getProtozoa());
            if(test.getMusclefibers()!=null)stool.setMusclefibers(test.getMusclefibers());
            if(test.getVegetables()!=null)stool.setVegetables(test.getVegetables());
            if(test.getStarch()!=null)stool.setStarch(test.getStarch());
            stoolRepo.saveAndFlush(stool);
            List<Test> result = new ArrayList<>();
            result.add(stool);
            return new Response<>(true, StaticsText.MessageForTest("stool Test", "Updated"), result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }

    @Override
    public Response<Test> DeleteTest(UUID id) {
        try{
            Stool stool=stoolRepo.stoolById(id);
            if(stool==null)return new Response<>(false, StaticsText.MessageForTest("stool Test", "not Found"));
            stool.setIsDeleted(true);
            stoolRepo.saveAndFlush(stool);
            List<Test> result = new ArrayList<>();
            result.add(stool);
            return new Response<>(true, StaticsText.MessageForTest("stool Test", "deleted"), result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> RetrieveAllTest() {
        try{
            List<Test> result=stoolRepo.getAllTestForSpecificType();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("stool Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("stool Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> RetrieveAllTestByMedicalInformationId(UUID id) {
        try{
            List<Test> result=stoolRepo.stoolByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("stool Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("stool Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test>  testDeletedByMedicalInformationId(UUID id) {
        try{
            List<Test> result=stoolRepo.stoolDeletedByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("stool Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("stool Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> TestByDate(Date startDate,Date endDate) {
        try{
            List<Test> result=stoolRepo.TestByDate(startDate,endDate);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("stool Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("stool Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public TypesTest GetType() {
        return TypesTest.Stool;
    }
}
