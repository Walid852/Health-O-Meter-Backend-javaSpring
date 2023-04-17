package com.example.projectdeploy.MedicalInformation;

import com.example.projectdeploy.Member.Repo.MemberRepo;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public MedicalInformation GetMedicalInformationById(UUID MedicalInfoId){
        return medicalInformationRepo.findMedicalInformationById(MedicalInfoId);
    }
    @Transactional
    public MedicalInformation GetMedicalInformationByUserId(UUID UserId){
        return medicalInformationRepo.findMedicalInformationByUserId(UserId);
    }
    @Transactional
    public MedicalInformation GetMedicalInformationByMemberId(UUID MemberId){
        return medicalInformationRepo.findMedicalInformationByMemberId(MemberId);
    }
    @Transactional
    public List<MedicalInformation> GetAllMedicalInformation(){
        return medicalInformationRepo.findAllMedicalInformation();
    }
    @Transactional
    public List<MedicalInformation> GetAllMedicalInformationForHeight(int height){
        return medicalInformationRepo.findAllMedicalInformationForHeight(height);
    }
    @Transactional
    public List<MedicalInformation> GetAllMedicalInformationForWeight(int weight){
        return medicalInformationRepo.findAllMedicalInformationForWeight(weight);
    }
    @Transactional
    public List<MedicalInformation> GetAllMedicalInformationForNumOfCubs(int numOfCup){
        return medicalInformationRepo.findAllMedicalInformationForNumOfCubs(numOfCup);
    }
    @Transactional
    public List<MedicalInformation> findAllMedicalInformationForBloodType(BloodType bloodType){
        return medicalInformationRepo.findAllMedicalInformationForBloodType(bloodType);
    }
}
