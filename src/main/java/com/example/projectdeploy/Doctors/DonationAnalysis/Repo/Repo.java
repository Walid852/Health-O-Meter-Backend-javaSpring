package com.example.projectdeploy.Doctors.DonationAnalysis.Repo;

import com.example.projectdeploy.Donate.Model.Donate;
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
    double getCountOfBloodType(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveAbilityToDonate=true")
    double getCountOfCanDonate(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveDiabetes=true")
    double getCountOfDiabetes(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveCancer=true")
    double getCountOfCancer(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveAids=true")
    double getCountOfAids(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveMalaria=true")
    double getCountOfMalaria(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveHighBloodPressure=true")
    double getCountOfHighPressure(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.lastTimeDonate >=?3")
    double getCountOfLastTimeToDonate(String bloodType, String city, Date cutOff);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveGeneticBloodDiseases=true")
    double getCountOfGeneticBloodDiseases(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveSevereAnemia=true")
    double getCountOfSevereAnemia(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.hepatitis_B=true")
    double getCountOfhepatitis_B(String bloodType,String city);

    @Query("select count(m.bloodType)  from  MedicalInformation m, UserLocation u, User uu where u.id=uu.location.id and uu.id=m.user.id and m.bloodType=?1 and u.city=?2 and m.haveSyphilis=true")
    double getCountOfSyphilis(String bloodType,String city);

    @Query("select distinct u.city from UserLocation u")
    ArrayList<String> getDistinctCities();

    @Query("select ul.lat,ul.lng from User u ,Donate do, UserLocation ul where do.RequestorMedicalInformation.id=u.id and ul.id=u.location.id and do.donateDate between ?1 and ?2")
    List<Object[]> getDonationLocation(Date from, Date to);











}
