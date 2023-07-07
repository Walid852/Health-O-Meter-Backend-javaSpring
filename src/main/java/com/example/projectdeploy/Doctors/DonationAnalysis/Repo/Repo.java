package com.example.projectdeploy.Doctors.DonationAnalysis.Repo;

import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.MedicalInformation.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface Repo extends JpaRepository<Donate, UUID> {

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2")
    double getCountOfBloodType(BloodType bloodType, String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveAbilityToDonate=true")
    double getCountOfCanDonate(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveDiabetes=true")
    double getCountOfDiabetes(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveCancer=true")
    double getCountOfCancer(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveAids=true")
    double getCountOfAids(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveMalaria=true")
    double getCountOfMalaria(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveHighBloodPressure=true")
    double getCountOfHighPressure(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.lastTimeDonate >=?3")
    double getCountOfLastTimeToDonate(BloodType bloodType, String city, Date cutOff);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveGeneticBloodDiseases=true")
    double getCountOfGeneticBloodDiseases(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveSevereAnemia=true")
    double getCountOfSevereAnemia(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.hepatitis_B=true")
    double getCountOfhepatitis_B(BloodType bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveSyphilis=true")
    double getCountOfSyphilis(BloodType bloodType,String city);

    @Query("select distinct u.city from UserLocation u")
    ArrayList<String> getDistinctCities();

    @Query("select u.user.location.lat,u.user.location.lng from MedicalInformation u ,Donate do where do.DonatorMedicalInformation.id=u.id and u.user.location.city=?1")
    List<Object[]> getDonationLocation(String city);











}
