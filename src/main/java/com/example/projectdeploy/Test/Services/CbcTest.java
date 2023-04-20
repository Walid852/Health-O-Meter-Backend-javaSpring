package com.example.projectdeploy.Test.Services;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.Test.Models.CBC;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Repos.CBCRepo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CbcTest implements BaseTest{
    @Autowired
    CBCRepo cbcRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    @Override
    public Response<Test> AddTest(Request test) {
        try {
            MedicalInformation medicalInformation = medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
            if (medicalInformation == null)
                return new Response<>(false, StaticsText.MessageForTest("cbc Test", "not Created"));
            CBC cbc = new CBC(medicalInformation, test.getIsDeleted(), test.getDate(),
                    test.getTest(), test.getHaemoglobin(), test.getRedCellsCount(),
                    test.getHaematocrit(), test.getMCV(), test.getMCH(), test.getMCHC()
                    , test.getPlateletsCount(), test.getPlateletsCount(), test.getNeutroPhils(),
                    test.getLymphoctyes(), test.getMonocytes(), test.getEosinophils(),
                    test.getNeutrophilsabsoluteCount(), test.getNeutrophilsabsoluteCount(),
                    test.getMonocytesabsolutecount(), test.getEosinophilsabsolutecount());
            cbcRepo.saveAndFlush(cbc);
            List<Test> result = new ArrayList<>();
            result.add(cbc);
            return new Response<>(true, StaticsText.MessageForTest("cbc Test", "Created"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> UpdateTest(Request test) {
        try{
            CBC cbc=cbcRepo.cbcById(test.getId());
            if(cbc==null)return new Response<>(false, StaticsText.MessageForTest("cbc Test", "not Updated"));
            if(test.getEosinophils()!=-1)cbc.setEosinophils(test.getEosinophils());
            if(test.getHaematocrit()!=-1)cbc.setHaematocrit(test.getHaematocrit());
            if(test.getEosinophilsabsolutecount()!=-1)cbc.setEosinophilsabsolutecount(test.getEosinophilsabsolutecount());
            if(test.getHaemoglobin()!=-1)cbc.setHaemoglobin(test.getHaemoglobin());
            if(test.getLymphocytesAbsoluteCount()!=-1)cbc.setLymphocytesAbsoluteCount(test.getLymphocytesAbsoluteCount());
            if(test.getLymphoctyes()!=-1)cbc.setLymphoctyes(test.getLymphoctyes());
            if(test.getMCH()!=-1)cbc.setMCH(test.getMCH());
            if(test.getMCV()!=-1)cbc.setMCV(test.getMCV());
            if(test.getNeutroPhils()!=-1)cbc.setNeutroPhils(test.getNeutroPhils());
            if(test.getMonocytes()!=-1)cbc.setMonocytes(test.getMonocytes());
            if(test.getMCHC()!=-1)cbc.setMCHC(test.getMCHC());
            if(test.getPlateletsCount()!=-1)cbc.setPlateletsCount(test.getPlateletsCount());
            if(test.getTotalLeucoCyticCount()!=-1)cbc.setTotalLeucoCyticCount(test.getTotalLeucoCyticCount());
            if(test.getRedCellsCount()!=-1)cbc.setRedCellsCount(test.getRedCellsCount());
            if(test.getMonocytesabsolutecount()!=-1)cbc.setMonocytesabsolutecount(test.getMonocytesabsolutecount());
            if(test.getNeutrophilsabsoluteCount()!=-1)cbc.setNeutrophilsabsoluteCount(test.getNeutrophilsabsoluteCount());
            cbcRepo.saveAndFlush(cbc);
            List<Test> result = new ArrayList<>();
            result.add(cbc);
            return new Response<>(true, StaticsText.MessageForTest("cbc Test", "Updated"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> DeleteTest(UUID id) {
        try {
            CBC cbc=cbcRepo.cbcById(id);
            if(cbc==null)return new Response<>(false, StaticsText.MessageForTest("cbc Test", "not Deleted"));
            cbc.setIsDeleted(true);
            cbcRepo.saveAndFlush(cbc);
            List<Test> result = new ArrayList<>();
            result.add(cbc);
            return new Response<>(true, StaticsText.MessageForTest("cbc Test", "Deleted"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> RetrieveAllTest() {
        try {
            List<Test> result=cbcRepo.getAllTestForSpecificType();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Cbc Tests", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("cbc Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> RetrieveAllTestByMedicalInformationId(UUID id) {
        try {
            List<Test> result=cbcRepo.cbcByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Cbc Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("cbc Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> testDeletedByMedicalInformationId(UUID id) {
        try {
            List<Test> result=cbcRepo.cbcDeletedByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Cbc Tests", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("cbc Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Override
    public Response<Test> TestByDate(Date startDate,Date endDate) {
        try {
            List<Test> result=cbcRepo.TestByDate(startDate,endDate);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Cbc Tests", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("cbc Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }

    @Override
    public TypesTest GetType() {
        return TypesTest.CBC;
    }
}
