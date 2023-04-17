package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Test.Models.Renal;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Repos.RenalRepo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
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
    public Test AddTest(Request test) {
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
        if(medicalInformation==null)return null;
        Renal renal=new Renal(test.getUrea(),test.getCreatinineInSerum(),
                test.getUricAcid(),medicalInformation,test.getIsDeleted(),test.getDate(),
                test.getTest());
        renalRepo.saveAndFlush(renal);
        return renal;
    }
    @Transactional
    @Override
    public Test UpdateTest(Request test) {
        Renal renal= renalRepo.renalById(test.getId());
        if(renal==null)return null;
        if(test.getUrea()!=-1)renal.setUrea(test.getUrea());
        if(test.getCreatinineInSerum()!=-1)renal.setCreatinineInSerum(test.getCreatinineInSerum());
        if(test.getUricAcid()!=-1)renal.setUricAcid(test.getUricAcid());
        renalRepo.save(renal);
        return renal;
    }
    @Transactional
    @Override
    public String DeleteTest(UUID id) {
        Renal renal= renalRepo.renalById(id);
        if(renal==null)return null;
        renal.setIsDeleted(true);
        renalRepo.save(renal);
        return "Done";
    }
    @Transactional
    @Override
    public List<Test> RetrieveAllTest() {

        return renalRepo.getAllTestForSpecificType();
    }
    @Transactional
    @Override
    public List<Test> RetrieveAllTestByMedicalInformationId(UUID id) {
        return renalRepo.renalByMedicalInformationId(id);
    }
    @Transactional
    @Override
    public List<Test> testDeletedByMedicalInformationId(UUID id) {
        return renalRepo.renalDeletedByMedicalInformationId(id);
    }

    @Override
    public List<Test> TestByDate(Date date) {
        return renalRepo.TestByDate(date);
    }

    @Transactional
    @Override
    public TypesTest GetType() {
        return TypesTest.Renal;
    }
}
