package com.example.projectdeploy.MedicalInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicalInformationRepo extends JpaRepository<MedicalInformation, UUID> {
    @Query("select M from MedicalInformation M where M.id=?1")
    public MedicalInformation findMedicalInformationById(UUID id);
    @Query("select M from MedicalInformation M where M.user.id=?1")
    public MedicalInformation findMedicalInformationByUserId(UUID id);
    @Query("select M from MedicalInformation M where M.member.id=?1")
    public MedicalInformation findMedicalInformationByMemberId(UUID id);
    @Query("select M from MedicalInformation M")
    public List<MedicalInformation> findAllMedicalInformation();
    @Query("select M from MedicalInformation M where M.bloodType>=?1")
    public List<MedicalInformation>  findAllMedicalInformationForBloodType(BloodType bloodType);
    @Query("select M from MedicalInformation M where M.CurrentHeight>=?1")
    public List<MedicalInformation>  findAllMedicalInformationForHeight(int height);
    @Query("select M from MedicalInformation M where M.CurrentWeight>=?1")
    public List<MedicalInformation>  findAllMedicalInformationForWeight(int weight);
    @Query("select M from MedicalInformation M where M.bloodType in?1 and M.CurrentWeight>50" +
            " and M.haveAids=false and M.haveCancer=false and M.haveDiabetes=false and M.haveGeneticBloodDiseases=false " +
            "and M.haveAbilityToDonate=true and M.haveHighBloodPressure=false and M.haveMalaria=false " +
            "and M.haveSevereAnemia=false and M.haveSyphilis=false and M.hepatitis_B=false and M.hepatitis_C=false " +
            "and M.isFever=false and M.user.location.government=?2")
    public List<MedicalInformation> findAllMedicalInformationValidateToDonate(List<BloodType> bloodType,String government);

}
