package com.example.projectdeploy.Test.Services;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Test.Models.CBC;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Repos.CBCRepo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
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
    public Test AddTest(Request test) {
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
        if(medicalInformation==null)return null;
        CBC cbc=new CBC(medicalInformation,test.getIsDeleted(),test.getDate(),
                test.getTest(),test.getHaemoglobin(), test.getRedCellsCount(),
                test.getHaematocrit(),test.getMCV(),test.getMCH(),test.getMCHC()
                ,test.getPlateletsCount(),test.getPlateletsCount(),test.getNeutroPhils(),
                test.getLymphoctyes(),test.getMonocytes(),test.getEosinophils(),
                test.getNeutrophilsabsoluteCount(),test.getNeutrophilsabsoluteCount(),
                test.getMonocytesabsolutecount(),test.getEosinophilsabsolutecount());
        cbcRepo.saveAndFlush(cbc);
        return cbc;
    }
    @Transactional
    @Override
    public Test UpdateTest(Request test) {
        CBC cbc=cbcRepo.cbcById(test.getId());
        if(cbc==null)return null;
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
        return cbc;
    }

    @Override
    public String DeleteTest(UUID id) {
        CBC cbc=cbcRepo.cbcById(id);
        if(cbc==null)return null;
        cbc.setIsDeleted(true);
        cbcRepo.saveAndFlush(cbc);
        return "Done";

    }

    @Override
    public List<Test> RetrieveAllTest() {
        return cbcRepo.getAllTestForSpecificType();

    }

    @Override
    public List<Test> RetrieveAllTestByMedicalInformationId(UUID id) {
        return cbcRepo.cbcByMedicalInformationId(id);
    }

    @Override
    public List<Test> testDeletedByMedicalInformationId(UUID id) {
        return cbcRepo.cbcDeletedByMedicalInformationId(id);
    }

    @Override
    public List<Test> TestByDate(Date date) {
        return cbcRepo.TestByDate(date);
    }

    @Override
    public TypesTest GetType() {
        return TypesTest.CBC;
    }
}
