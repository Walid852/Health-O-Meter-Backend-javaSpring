package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Service;

import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model.SugarBloodTest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model.SugarTestCategory;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Repo.SugarBloodTestRepo;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Requset.SugarTestRequest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.SugarAnalysis.CategoryAnalysis;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.SugarAnalysis.DateReq;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.SugarAnalysis.SugarAnalysis;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SugarBloodTestService {

    @Autowired
    SugarBloodTestRepo testRepo;

    @Autowired
    MedicalInformationRepo medicalInformationRepo;

    @Transactional
    public Response<SugarBloodTest>  addRead(SugarTestRequest testRequest){
        try{
            SugarBloodTest test=new SugarBloodTest();
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(testRequest.getMedicalInformationId());
            if(medicalInformation==null||testRequest.getRead()==-1)return new Response<>(false, StaticsText.MessageForTest("error not have medical Information", "or error in read"));
            test.setMedicalInformation(medicalInformation);
            test.setReadd(testRequest.getRead());
            test.setDate(testRequest.getDate());
            test.setTime(testRequest.getTime());
            test.setTestPeriod(testRequest.getPeriod());
            test.setAm_pm(testRequest.getAm_pm());
            System.out.println(test);
            test.setSugarTestCategory(getDiabetesCategory(testRequest.getRead()));
            testRepo.save(test);
            List<SugarBloodTest> result = new ArrayList<>();
            result.add(test);
            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "added"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    public SugarTestCategory getDiabetesCategory(int read){
        SugarTestCategory sugarTestCategory;
        if(read<=70)
            sugarTestCategory=SugarTestCategory.Low;
        else if(read<=179)
            sugarTestCategory=SugarTestCategory.Normal;
        else
            sugarTestCategory=SugarTestCategory.High;

        return sugarTestCategory;

    }

    @Transactional
    public Response<SugarBloodTest> updateRead(SugarTestRequest testRequest){
        try{
            SugarBloodTest updatedTest=new SugarBloodTest();

            if(testRepo.findById(testRequest.getTestId()).isPresent()){
                updatedTest=testRepo.getSugarBloodTestById(testRequest.getTestId());
                System.out.println(updatedTest.getDate());
                if(testRequest.getPeriod()!=null)updatedTest.setTestPeriod(testRequest.getPeriod());
                if(testRequest.getRead()!=-1){
                    updatedTest.setReadd(testRequest.getRead());
                    updatedTest.setSugarTestCategory(getDiabetesCategory(testRequest.getRead()));
                }
                System.out.println(testRequest.getDate());
                System.out.println(testRequest.getTime());
                if(testRequest.getDate()!=null){
                    System.out.println(1);
                    updatedTest.setDate(testRequest.getDate());
                }
                if(testRequest.getTime()!=null){
                    System.out.println(2);
                    updatedTest.setTime(testRequest.getTime());
                }
                updatedTest.setAm_pm(testRequest.getAm_pm());
            }
            testRepo.saveAndFlush(updatedTest);
            List<SugarBloodTest> result = new ArrayList<>();
            result.add(updatedTest);
            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Updated"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<SugarBloodTest> deleteRead(UUID id){
        try{
            SugarBloodTest deletedTest =new SugarBloodTest();
            if(testRepo.findById(id).isPresent()){
                deletedTest =testRepo.findById(id).get();
                deletedTest.setDeleted(true);
                testRepo.save(deletedTest);
            }
            List<SugarBloodTest> result = new ArrayList<>();
            result.add(deletedTest);
            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Deleted"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Transactional
    public Response<SugarBloodTest> getSugarBloodTestByMedicalInformationId(UUID medicalInformationId){
        try{
            List<SugarBloodTest> result=testRepo.getSugarBloodTestByMedicalInformationId(medicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Sugar Blood Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<SugarBloodTest> getDeletedSugarBloodTestByMedicalInformationId(UUID medicalInformationId){
        try{
            List<SugarBloodTest> result=testRepo.getDeletedSugarBloodTestByMedicalInformationId(medicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Sugar Blood Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<SugarBloodTest> getAllSugarBloodTest(){
        try{
            List<SugarBloodTest> result=testRepo.getAllSugarBloodTest();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Sugar Blood Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<SugarBloodTest> getSugarBloodTestById(UUID id){
        SugarBloodTest sugarBloodTest= testRepo.getSugarBloodTestById(id);
        try{

            if(sugarBloodTest==null)return new Response<>(false, StaticsText.MessageForTest("Sugar Blood Test", "not Found"));
            List<SugarBloodTest> result = new ArrayList<>();
            result.add(sugarBloodTest);
            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<SugarBloodTest> getSugarBloodTestByTestPeriod(TestPeriod testPeriod){
        try{
            List<SugarBloodTest> result=testRepo.getSugarBloodTestByTestPeriod(testPeriod);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Sugar Blood Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<SugarBloodTest> getSugarBloodTestByRead(int read){
        try{
            List<SugarBloodTest> result=testRepo.getSugarBloodTestByRead(read);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Sugar Blood Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<SugarBloodTest> filterTestByDateUser(UUID medicalInformationId, Date start,Date end){
        try{
            List<SugarBloodTest> result=testRepo.filterTestByDateUser(medicalInformationId,start,end);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Sugar Blood Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Sugar Blood Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }


    @Transactional
    public Response<SugarAnalysis> SugarAnalysis(DateReq dateReq){
        List<SugarAnalysis> reads=new ArrayList<>();
        List<Object[]> analysis= testRepo.calculateAverageByDate(dateReq.getMedicalId(),dateReq.getFrom(),dateReq.getTo());
        for(Object[] obj:analysis){
            SugarAnalysis sugarAnalysis= new SugarAnalysis();
            sugarAnalysis.setDate((Date) obj[0]);
            sugarAnalysis.setAvgRead((Double) obj[1]);
            reads.add(sugarAnalysis);
        }
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"),reads);
    }

    @Transactional
    public Response<CategoryAnalysis> sCategoryAnalysis(DateReq dateReq){
        List<CategoryAnalysis> counts=new ArrayList<>();
        List<Object[]> analysis= testRepo.calculateCategory(dateReq.getMedicalId(),dateReq.getFrom(),dateReq.getTo());
        for(Object[] obj:analysis){
            CategoryAnalysis categoryAnalysis= new CategoryAnalysis();
            categoryAnalysis.setCategory(obj[0].toString());
            categoryAnalysis.setCount((Long) obj[1]);
            counts.add(categoryAnalysis);
        }
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"),counts);
    }
}
