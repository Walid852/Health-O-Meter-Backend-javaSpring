package com.example.projectdeploy.MedicalInformation;

import com.example.projectdeploy.Member.Repo.MemberRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FetchServiceMedicalInformation {
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    MemberRepo memberRepo;
    @Transactional
    public Response<MedicalInformation> GetMedicalInformationById(UUID MedicalInfoId){
        try{
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(MedicalInfoId);
            List<MedicalInformation> result=new ArrayList<>();
            if(medicalInformation!=null)result.add(medicalInformation);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Medical Information", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<MedicalInformation> GetMedicalInformationByUserId(UUID UserId){
        try{
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationByUserId(UserId);
            List<MedicalInformation> result=new ArrayList<>();
            if(medicalInformation!=null)result.add(medicalInformation);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Medical Information", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<MedicalInformation> GetMedicalInformationByMemberId(UUID MemberId){
        try{
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationByMemberId(MemberId);
            List<MedicalInformation> result=new ArrayList<>();
            if(medicalInformation!=null)result.add(medicalInformation);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Medical Information", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<MedicalInformation> GetAllMedicalInformation(){
        try{

            List<MedicalInformation> result=medicalInformationRepo.findAllMedicalInformation();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Medical Information", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<MedicalInformation> GetAllMedicalInformationForHeight(int height){
        try{
            List<MedicalInformation> result=medicalInformationRepo.findAllMedicalInformationForHeight(height);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Medical Information", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<MedicalInformation> GetAllMedicalInformationForWeight(int weight){
        try{
            List<MedicalInformation> result=medicalInformationRepo.findAllMedicalInformationForWeight(weight);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Medical Information", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public Response<MedicalInformation> findAllMedicalInformationForBloodType(BloodType bloodType){
        try{
            List<MedicalInformation> result=medicalInformationRepo.findAllMedicalInformationForBloodType(bloodType);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("Medical Information", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Retrieved"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
}
