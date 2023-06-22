package com.example.projectdeploy.MedicalInformation;

import com.example.projectdeploy.Member.Model.Member;
import com.example.projectdeploy.Member.Repo.MemberRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Gender;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class CrudServiceMedicalInformation {
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    MemberRepo memberRepo;
    @Transactional
    public Response<MedicalInformation> createMedicalInformation(CreateMedicalInformation createMedicalInformation){
        try {
            MedicalInformation medicalInformation = new MedicalInformation();
            MedicalInformation MM = medicalInformationRepo.findMedicalInformationByUserId(createMedicalInformation.getUserId());
            if (MM != null) return new Response<>(false, StaticsText.MessageForTest("you have medical information", ""));
            if (createMedicalInformation.getUserId() != null) {
                User user = userRepo.findByUserId(createMedicalInformation.getUserId());
                medicalInformation.setUser(user);
            }
            if (createMedicalInformation.getMemberId() != null) {
                Member member = memberRepo.findMemberById(createMedicalInformation.getMemberId());
                medicalInformation.setMember(member);
            }
            medicalInformation.setBloodType(createMedicalInformation.getBloodType());
            medicalInformation.setCurrentWeight(createMedicalInformation.CurrentWeight);
            System.out.println(createMedicalInformation.CurrentHeight + createMedicalInformation.CurrentWeight);
            medicalInformation.setCurrentHeight(createMedicalInformation.CurrentHeight);
            medicalInformationRepo.save(medicalInformation);
            List<MedicalInformation> result = new ArrayList<>();
            result.add(medicalInformation);
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Created"), result);
        }
        catch (Exception e){
            return new Response<>(false,StaticsText.MessageForTestError());
        }
    }
    public List<MedicalInformation> ValidateToDonate(BloodType bloodType,String government){
        List<BloodType> bloodTypeList=GivenAndReceiveForBloodType(bloodType);
        System.out.println(bloodType);
        System.out.println(government);
        List<MedicalInformation> medicalInformationList=medicalInformationRepo.findAllMedicalInformationValidateToDonate(bloodTypeList,government);
        System.out.println(medicalInformationList.size());
        List<MedicalInformation> result=new LinkedList<>();
        for (MedicalInformation MI:medicalInformationList) {
            System.out.println(MI.getUser().getId());
            int F=0;
            int y=0;
            if(MI.lastTimeDonate!=null){
            long now = System.currentTimeMillis();
            Date DateNow = new Date(now);
            LocalDate date =DateNow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate date1 =MI.lastTimeDonate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Period diff = Period.between(date,date1);
            if((Math.abs(diff.getDays())<56))F=1;
            }
            if(MI.getUser().getAge()<18
                    ||MI.getUser().getAge()>65
                    ||(MI.getUser().getGender()== Gender.male&&(MI.hemoglobin>17||MI.hemoglobin<14))
                    ||(MI.getUser().getGender()== Gender.female&&(MI.hemoglobin>14||MI.hemoglobin<12))
                    ||F==1){
                System.out.println(true);
            }
            else {
                result.add(MI);
            }
        }
        return result;
    }
    @Transactional
    public Response<MedicalInformation> UpdateMedicalInformation(UpdateMedicalInformation updateMedicalInformation){
        try {
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(updateMedicalInformation.getMedicalInfoId());
            if(medicalInformation==null)return new Response<>(false, StaticsText.MessageForTest("medical information", "not found"));
            if(updateMedicalInformation.getBloodType()!=null) medicalInformation.setBloodType(updateMedicalInformation.getBloodType());
            if(updateMedicalInformation.CurrentWeight!=0) medicalInformation.setCurrentWeight(updateMedicalInformation.CurrentWeight);
            if(updateMedicalInformation.CurrentHeight!=0) medicalInformation.setCurrentHeight(updateMedicalInformation.CurrentHeight);
            medicalInformation.setFever(updateMedicalInformation.isFever());
            medicalInformation.setHaveAids(updateMedicalInformation.getHaveAids());
            medicalInformation.setHepatitis_B(updateMedicalInformation.getHepatitis_B());
            medicalInformation.setHepatitis_C(updateMedicalInformation.getHepatitis_C());
            medicalInformation.setHaveMalaria(updateMedicalInformation.getHaveMalaria());
            medicalInformation.setHaveSyphilis(updateMedicalInformation.getHaveSyphilis());
            medicalInformation.setHaveSevereAnemia(updateMedicalInformation.getHaveSevereAnemia());
            medicalInformation.setHaveCancer(updateMedicalInformation.getHaveCancer());
            medicalInformation.setHaveDiabetes(updateMedicalInformation.getHaveDiabetes());
            medicalInformation.setHaveHighBloodPressure(updateMedicalInformation.getHaveHighBloodPressure());
            medicalInformation.setHaveGeneticBloodDiseases(updateMedicalInformation.getHaveGeneticBloodDiseases());
            medicalInformation.setHaveAbilityToDonate(updateMedicalInformation.getHaveAbilityToDonate());
            medicalInformationRepo.save(medicalInformation);
            List<MedicalInformation> result = new ArrayList<>();
            result.add(medicalInformation);
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Updated"), result);
        }
        catch (Exception e){
            return new Response<>(false,StaticsText.MessageForTestError());
        }
    }
    @Transactional
    public List<BloodType> GivenAndReceiveForBloodType(BloodType bloodType){
        List<BloodType> bloodTypeList=new LinkedList<>();
        bloodTypeList.add(BloodType.O_Negative);
        if(bloodType==BloodType.A_Positive){
            bloodTypeList.add(BloodType.A_Positive);
            bloodTypeList.add(BloodType.O_Positive);
            bloodTypeList.add(BloodType.A_Negative);
        }
        if(bloodType==BloodType.O_Positive){
            bloodTypeList.add(BloodType.O_Positive);
        }
        if(bloodType==BloodType.B_Positive){
            bloodTypeList.add(BloodType.B_Positive);
            bloodTypeList.add(BloodType.B_Negative);
            bloodTypeList.add(BloodType.O_Positive);
        }
        if(bloodType==BloodType.A_Negative){
            bloodTypeList.add(BloodType.A_Negative);
        }
        if(bloodType==BloodType.B_Negative){
            bloodTypeList.add(BloodType.B_Negative);
        }
        if(bloodType==BloodType.AB_Negative){
           bloodTypeList.add(BloodType.AB_Negative);
            bloodTypeList.add(BloodType.B_Negative);
            bloodTypeList.add(BloodType.A_Negative);
        }
        if (bloodType==BloodType.AB_Positive){
            bloodTypeList.add(BloodType.A_Positive);
            bloodTypeList.add(BloodType.A_Negative);
            bloodTypeList.add(BloodType.O_Positive);
            bloodTypeList.add(BloodType.AB_Positive);
            bloodTypeList.add(BloodType.AB_Negative);
            bloodTypeList.add(BloodType.B_Positive);
            bloodTypeList.add(BloodType.B_Negative);
        }
        return bloodTypeList;
    }
    @Transactional
    public Response<MedicalInformation> DeleteMedicalInformation(UUID MedicalInfoId){
        try {
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(MedicalInfoId);
            if(medicalInformation==null)return new Response<>(false, StaticsText.MessageForTest("medical information", "not found"));
            medicalInformationRepo.delete(medicalInformation);
            List<MedicalInformation> result = new ArrayList<>();
            result.add(medicalInformation);
            return new Response<>(true, StaticsText.MessageForTest("Medical Information", "Updated"), result);
        }
        catch (Exception e){
            return new Response<>(false,StaticsText.MessageForTestError());
        }

    }



}
