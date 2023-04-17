package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Test.Models.Stool;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Repos.StoolRepo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class StoolTest implements BaseTest{
    @Autowired
    StoolRepo stoolRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Override
    public Test AddTest(Request test) {
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
        if(medicalInformation==null)return null;
        Stool stool=new Stool(medicalInformation, test.getIsDeleted(),test.getDate()
        ,test.getTest(),test.getColor(),test.getConsistency(),test.getFoodParticles()
        ,test.getMucus(),test.getBlood(),test.getStarch(),test.getMusclefibers()
         ,test.getVegetables(),test.getProtozoa(),test.getCiliates()
        );
        stoolRepo.saveAndFlush(stool);
        return stool;
    }

    @Override
    public Test UpdateTest(Request test) {
        Stool stool=stoolRepo.stoolById(test.getId());
        if(stool==null)return null;
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
        return stool;
    }

    @Override
    public String DeleteTest(UUID id) {
        Stool stool=stoolRepo.stoolById(id);
        if(stool==null)return null;
        stool.setIsDeleted(true);
        stoolRepo.saveAndFlush(stool);
        return "Done";
    }

    @Override
    public List<Test> RetrieveAllTest() {

        return  stoolRepo.getAllTestForSpecificType();
    }

    @Override
    public List<Test> RetrieveAllTestByMedicalInformationId(UUID id) {
        return  stoolRepo.stoolByMedicalInformationId(id);
    }

    @Override
    public List<Test>  testDeletedByMedicalInformationId(UUID id) {
        return  stoolRepo.stoolDeletedByMedicalInformationId(id);
    }

    @Override
    public List<Test> TestByDate(Date date) {

        return  stoolRepo.TestByDate(date);
    }

    @Override
    public TypesTest GetType() {
        return TypesTest.Stool;
    }
}
