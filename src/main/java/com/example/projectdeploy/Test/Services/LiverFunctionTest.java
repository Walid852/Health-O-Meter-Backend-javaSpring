package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Test.Models.LiverFunction;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Repos.LiverFunctionRepo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class LiverFunctionTest implements BaseTest{
    @Autowired
    LiverFunctionRepo liverFunctionRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    @Override
    public Test AddTest(Request test) {
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
        if(medicalInformation==null)return null;
        LiverFunction liverFunction=new LiverFunction(
                medicalInformation,test.getIsDeleted(),test.getDate(),test.getTest(),
                test.getGammaGT(),test.getBilirubin_Total(),test.getBilirubin_Direct(),
                test.getAST(),test.getALT(),test.getAlk(),test.getTotalProtein(),test.getAlbumin()
        );
        liverFunctionRepo.saveAndFlush(liverFunction);
        return liverFunction;
    }
    @Transactional
    @Override
    public Test UpdateTest(Request test) {
        LiverFunction liverFunction=liverFunctionRepo.liverFunctionById(test.getId());
        if (liverFunction==null)return null;
        if(test.getAlbumin()!=-1)liverFunction.setAlbumin(test.getAlbumin());
        if(test.getAlk()!=-1)liverFunction.setAlk(test.getAlk());
        if(test.getALT()!=-1) liverFunction.setALT(test.getALT());
        if(test.getAST()!=-1)liverFunction.setAST(test.getAST());
        if(test.getBilirubin_Direct()!=-1)liverFunction.setBilirubin_Direct(test.getBilirubin_Direct());
        if(test.getBilirubin_Total()!=-1) liverFunction.setBilirubin_Total(test.getBilirubin_Total());
        if(test.getGammaGT()!=-1)liverFunction.setGammaGT(test.getGammaGT());
        if(test.getTotalProtein()!=-1)liverFunction.setTotalProtein(test.getTotalProtein());
        liverFunctionRepo.saveAndFlush(liverFunction);
        return liverFunction;
    }
    @Transactional
    @Override
    public String DeleteTest(UUID id) {
        LiverFunction liverFunction=liverFunctionRepo.liverFunctionById(id);
        if (liverFunction==null)return null;
        liverFunction.setIsDeleted(true);
        liverFunctionRepo.saveAndFlush(liverFunction);
        return "Done";
    }
    @Transactional
    @Override
    public List<Test> RetrieveAllTest() {

        return liverFunctionRepo.getAllTestForSpecificType();
    }
    @Transactional
    @Override
    public List<Test> RetrieveAllTestByMedicalInformationId(UUID id) {

        return liverFunctionRepo.liverFunctionByMedicalInformationId(id);
    }
    @Transactional
    @Override
    public List<Test> testDeletedByMedicalInformationId(UUID id) {

        return liverFunctionRepo.liverFunctionDeletedByMedicalInformationId(id);
    }
    @Transactional
    @Override
    public List<Test> TestByDate(Date date) {
        return liverFunctionRepo.TestByDate(date);
    }
    @Transactional
    @Override
    public TypesTest GetType() {
        return TypesTest.LiverFunction;
    }
}
