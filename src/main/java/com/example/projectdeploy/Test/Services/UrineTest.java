package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Models.Urine;
import com.example.projectdeploy.Test.Repos.UrineRpo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UrineTest implements BaseTest{
    @Autowired
    UrineRpo urineRpo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;

    @Transactional
    @Override
    public Response<Test> AddTest(Request test) {
        try {
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
            if(medicalInformation==null)return new Response<>(false, StaticsText.MessageForTest("urine Test", "not Found"));
            Urine urine=new Urine(medicalInformation,test.getIsDeleted(),test.getDate(),
                    test.getTest(),test.getColor(),test.getClarity(),test.getSpecificGravity()
                    ,test.getUrinePH(),test.getProtein(),test.getGlucose(),test.getKetone()
                    ,test.getUrinebilirubin(),test.getNitrite(),test.getCrystals(),test.getCasts());
            urineRpo.saveAndFlush(urine);
            List<Test> result = new ArrayList<>();
            result.add(urine);
            return new Response<>(true, StaticsText.MessageForTest("urine Test", "Created"), result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    @Override
    public Response<Test> UpdateTest(Request test) {
        try {
            Urine urine=urineRpo.UrineById(test.getId());
            if (urine==null)return new Response<>(false, StaticsText.MessageForTest("urine Test", "not Found"));
            if(test.getSpecificGravity()!=-1)urine.setSpecificGravity(test.getSpecificGravity());
            if(test.getUrinePH()!=null)urine.setUrinePH(test.getUrinePH());
            if(test.getDate()!=null){urine.setDate(test.getDate());}
            if(test.getCasts()!=null)urine.setCasts(test.getCasts());
            if(test.getCrystals()!=null)urine.setCrystals(test.getCrystals());
            if(test.getColor()!=null)urine.setColor(test.getColor());
            if(test.getGlucose()!=null)urine.setGlucose(test.getGlucose());
            if(test.getKetone()!=null)urine.setKetone(test.getKetone());
            if(test.getProtein()!=null)urine.setProtein(test.getProtein());
            if(test.getUrinebilirubin()!=null)urine.setUrinebilirubin(test.getUrinebilirubin());
            if(test.getClarity()!=null)urine.setClarity(test.getClarity());
            if(test.getNitrite()!=null)urine.setNitrite(test.getNitrite());
            urineRpo.saveAndFlush(urine);
            List<Test> result = new ArrayList<>();
            result.add(urine);
            return new Response<>(true, StaticsText.MessageForTest("urine Test", "Updated"), result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    @Transactional
    @Override
    public Response<Test> DeleteTest(UUID id) {
        try {
            Urine urine=urineRpo.UrineById(id);
            if (urine==null)return new Response<>(false, StaticsText.MessageForTest("urine Test", "not Found"));
            urine.setIsDeleted(true);
            urineRpo.saveAndFlush(urine);
            List<Test> result = new ArrayList<>();
            result.add(urine);
            return new Response<>(true, StaticsText.MessageForTest("urine Test", "Deleted"), result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }

    @Override
    public Response<Test> RetrieveAllTest() {
        try{
            List<Test> result= urineRpo.getAllTestForSpecificType();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("urine Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("urine Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }

    @Override
    public Response<Test> RetrieveAllTestByMedicalInformationId(UUID id) {

        try{
            List<Test> result= urineRpo.urineByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("urine Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("urine Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> testDeletedByMedicalInformationId(UUID id) {
        try{
            List<Test> result=urineRpo.urineDeletedByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("urine Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("urine Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> TestByDate(Date startDate,Date endDate) {
        try{
            List<Test> result=urineRpo.TestByDate(startDate,endDate);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("urine Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("urine Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public TypesTest GetType() {
        return TypesTest.Urine;
    }
}
