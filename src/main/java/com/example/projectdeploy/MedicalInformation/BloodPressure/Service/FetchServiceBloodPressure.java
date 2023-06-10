package com.example.projectdeploy.MedicalInformation.BloodPressure.Service;

import com.example.projectdeploy.MedicalInformation.BloodPressure.Model.BloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.Repo.BloodPressureRepo;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.BloodPressureCategory;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.CategoryCount;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.DateReq;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.PressureAnalysis;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model.SugarBloodTest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.SugarAnalysis.SugarAnalysis;
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
public class FetchServiceBloodPressure {
    @Autowired
    BloodPressureRepo bloodPressureRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;

    public Response<BloodPressure> GetBloodPressureById(UUID id){
        try{

            BloodPressure bloodPressure= bloodPressureRepo.findBloodPressureById(id);

            if(bloodPressure==null)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not Found"));
            List<BloodPressure> result = new ArrayList<>();
            result.add(bloodPressure);

            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<BloodPressure> GetBloodPressureByMedicalInformationId(UUID MedicalInformationId){
        try{
            List<BloodPressure> result=bloodPressureRepo.findBloodPressureByMedicalInformationId(MedicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<BloodPressure> findBloodPressureDeleted(UUID MedicalInformationId){
        try{
            List<BloodPressure> result=bloodPressureRepo.findBloodPressureDeleted(MedicalInformationId);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<BloodPressure> GetAllBloodPressure(){
        try{
            List<BloodPressure> result=bloodPressureRepo.findAllBloodPressure();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<BloodPressure> GetAllBloodPressureByDate(Date start, Date endDate){
        try{
            List<BloodPressure> result=bloodPressureRepo.findAllBloodPressureByDate(start,endDate);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<BloodPressure> GetBloodPressureByDiastolic(int diastolic){
        try{
            List<BloodPressure> result=bloodPressureRepo.findBloodPressureByDiastolic(diastolic);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<BloodPressure> GetBloodPressureBySystolic(int systolic){

        try{
            List<BloodPressure> result=bloodPressureRepo.findBloodPressureBySystolic(systolic);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<BloodPressure> GetBloodPressureByBloodPressureCategory( BloodPressureCategory bloodPressureCategory){
        try{
            List<BloodPressure> result= bloodPressureRepo.findBloodPressureByBloodPressureCategory(bloodPressureCategory);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Blood Pressure Test", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("Blood Pressure Test", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<PressureAnalysis> pressureAnalysis(DateReq dateReq){
        List<PressureAnalysis> reads=new ArrayList<>();
        List<Object[]> analysis= bloodPressureRepo.calculateAverageByDate(dateReq.getMedicalId(),dateReq.getFrom(),dateReq.getTo());
        for(Object[] obj:analysis){
            PressureAnalysis pressureAnalysis= new PressureAnalysis();
            pressureAnalysis.setDate((Date) obj[0]);
            pressureAnalysis.setAvgSystolic((Double) obj[1]);
            pressureAnalysis.setAvgDiastolic((Double) obj[2]);
            reads.add(pressureAnalysis);
        }
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"),reads);
    }

    @Transactional
    public Response<CategoryCount> countCategories(DateReq dateReq){
        List<CategoryCount> counts=new ArrayList<>();
        List<Object[]> analysis= bloodPressureRepo.calculateCategory(dateReq.getMedicalId(),dateReq.getFrom(),dateReq.getTo());
        for(Object[] obj:analysis){
            CategoryCount categoryCount= new CategoryCount();
            categoryCount.setCategory(obj[0].toString());
            categoryCount.setCount((Long) obj[1]);
            counts.add(categoryCount);
        }
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"),counts);
    }
}
