package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Service;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model.SugarBloodTest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Repo.SugarBloodTestRepo;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Requset.SugarTestRequest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SugarBloodTestService {

    @Autowired
    SugarBloodTestRepo testRepo;

    @Autowired
    MedicalInformationRepo medicalInformationRepo;

    @Transactional
    public SugarBloodTest addRead(SugarTestRequest testRequest){
        SugarBloodTest test=new SugarBloodTest();
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(testRequest.getMedicalInformationId());
        if(medicalInformation==null||testRequest.getRead()==-1)return null;
        test.setMedicalInformation(medicalInformation);
        test.setReadd(testRequest.getRead());
        test.setDate(testRequest.getDate());
        test.setTime(testRequest.getTime());
        test.setTestPeriod(testRequest.getPeriod());
        System.out.println(test);
        testRepo.save(test);
        return test;
    }

    @Transactional
    public SugarBloodTest updateRead(SugarTestRequest testRequest){
        SugarBloodTest updatedTest=new SugarBloodTest();

        if(testRepo.findById(testRequest.getTestId()).isPresent()){
            updatedTest=testRepo.findById(testRequest.getTestId()).get();
            if(testRequest.getPeriod()!=null)updatedTest.setTestPeriod(testRequest.getPeriod());
            if(testRequest.getRead()!=-1)updatedTest.setReadd(testRequest.getRead());
            if(testRequest.getDate()!=null)updatedTest.setDate(testRequest.getDate());
            if(testRequest.getTime()!=null)updatedTest.setTime(testRequest.getTime());
            testRepo.save(updatedTest);
        }
        return updatedTest;
    }

    @Transactional
    public String deleteRead(UUID id){
        SugarBloodTest deletedTest =new SugarBloodTest();

        if(testRepo.findById(id).isPresent()){
            deletedTest =testRepo.findById(id).get();
            deletedTest.setDeleted(true);
            testRepo.save(deletedTest);
        }
        return "deleted Test";
    }

    @Transactional
    public List<SugarBloodTest> getSugarBloodTestByMedicalInformationId(UUID medicalInformationId){
        return testRepo.getSugarBloodTestByMedicalInformationId(medicalInformationId);
    }
    @Transactional
    public List<SugarBloodTest> getDeletedSugarBloodTestByMedicalInformationId(UUID medicalInformationId){
        return testRepo.getDeletedSugarBloodTestByMedicalInformationId(medicalInformationId);
    }
    @Transactional
    public List<SugarBloodTest> getAllSugarBloodTest(){
        return testRepo.getAllSugarBloodTest();
    }
    @Transactional
    public SugarBloodTest getSugarBloodTestById(UUID id){
        return testRepo.getSugarBloodTestById(id);
    }
    @Transactional
    public List<SugarBloodTest> getSugarBloodTestByTestPeriod(TestPeriod testPeriod){
        return testRepo.getSugarBloodTestByTestPeriod(testPeriod);
    }
    @Transactional
    public List<SugarBloodTest> getSugarBloodTestByRead(int read){
        return testRepo.getSugarBloodTestByRead(read);
    }
    @Transactional
    public List<SugarBloodTest> filterTestByDateUser(UUID medicalInformationId, Date start,Date end){
        return testRepo.filterTestByDateUser(medicalInformationId,start,end);
    }



}
