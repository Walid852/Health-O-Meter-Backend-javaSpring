package com.example.projectdeploy.Doctors.DiseaseAnalysis.Repo;

import com.example.projectdeploy.Disease.Models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

public interface DiAnalysisRepo extends JpaRepository<Disease, UUID> {

    @Query("select distinct d.name from Disease d")
    ArrayList<String> getDistinctDisease();

    @Query("select count(d.name) from Disease d where d.name=?1 and d.startDate between ?2 and ?3")
    int getCountOfPeople(String disease, Date from,Date to);

    @Query("select count(d.isCured) from Disease d where d.isCured=true and d.name=?1 and d.startDate between ?2 and ?3")
    int getCuredCount(String city, Date from,Date to);

    @Query("select max(u.age) from User u, Disease d,MedicalInformation m where d.medicalInformation.id=m.id and m.user.id=u.id and d.name=?1 and d.startDate between ?2 and ?3")
    int getMaxAge(String disease, Date from,Date to);

    @Query("select min(u.age) from User u, Disease d,MedicalInformation m where d.medicalInformation.id=m.id and m.user.id=u.id and d.name=?1 and d.startDate between ?2 and ?3")
    int getMinAge(String disease, Date from,Date to);

    @Query("select avg(u.age) from User u, Disease d,MedicalInformation m where d.medicalInformation.id=m.id and m.user.id=u.id and d.name=?1 and d.startDate between ?2 and ?3")
    int getAvgAge(String disease, Date from,Date to);

    @Query("select count(u.gender) from User u, Disease d,MedicalInformation m where d.medicalInformation.id=m.id and m.user.id=u.id and d.name=?1 and u.gender='Male' and d.startDate between ?2 and ?3")
    int getCountMale(String disease, Date from,Date to);

    @Query("select max(d.daysToCure) from Disease d where d.name=?1 and d.startDate between ?2 and ?3")
    int maxToRecover(String disease, Date from,Date to);

    @Query("select min(d.daysToCure) from Disease d where d.name=?1 and d.startDate between ?2 and ?3")
    int minToRecover(String disease, Date from,Date to);

    @Query("select avg(d.daysToCure) from Disease d where d.name=?1 and d.startDate between ?2 and ?3")
    int avgToRecover(String disease, Date from,Date to);

}
