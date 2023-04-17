package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Models.Urine;
import com.example.projectdeploy.Test.Repos.UrineRpo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
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
    public Test AddTest(Request test) {
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
        if(medicalInformation==null)return null;
        Urine urine=new Urine(medicalInformation,test.getIsDeleted(),test.getDate(),
                test.getTest(),test.getColor(),test.getClarity(),test.getSpecificGravity()
        ,test.getUrinePH(),test.getProtein(),test.getGlucose(),test.getKetone()
        ,test.getUrinebilirubin(),test.getNitrite(),test.getCrystals(),test.getCasts());
        urineRpo.saveAndFlush(urine);
        return urine;
    }
    @Transactional
    @Override
    public Test UpdateTest(Request test) {
        Urine urine=urineRpo.UrineById(test.getId());
        if (urine==null)return null;
        if(test.getSpecificGravity()!=-1)urine.setSpecificGravity(test.getSpecificGravity());
        if(test.getUrinePH()!=null)urine.setUrinePH(test.getUrinePH());
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
        return urine;
    }
    @Transactional
    @Override
    public String DeleteTest(UUID id) {
        Urine urine=urineRpo.UrineById(id);
        if (urine==null)return null;
        urine.setIsDeleted(true);
        urineRpo.saveAndFlush(urine);
        return "Done";
    }

    @Override
    public List<Test> RetrieveAllTest() {

        return  urineRpo.getAllTestForSpecificType();
    }

    @Override
    public List<Test> RetrieveAllTestByMedicalInformationId(UUID id) {
        return  urineRpo.urineByMedicalInformationId(id);
    }

    @Override
    public List<Test> testDeletedByMedicalInformationId(UUID id) {
        return  urineRpo.urineDeletedByMedicalInformationId(id);
    }

    @Override
    public List<Test> TestByDate(Date date) {
        return  urineRpo.TestByDate(date);
    }

    @Override
    public TypesTest GetType() {
        return TypesTest.Urine;
    }
}
