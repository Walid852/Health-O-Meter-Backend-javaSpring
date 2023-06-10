package com.example.projectdeploy.Doctors.DonationAnalysis.Service;

import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DateReq;
import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DiseaseLocation;
import com.example.projectdeploy.Doctors.DonationAnalysis.DTO.DTO;
import com.example.projectdeploy.Doctors.DonationAnalysis.Repo.Repo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.Test.Models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.*;

@Service
public class DonationAnalysisService {

    @Autowired
    Repo repo;



    List<String> bloodTypes = new ArrayList<>(Arrays.asList(
            "A_Positive",
            "A_Negative",
            "B_Positive",
            "B_Negative",
            "O_Positive",
            "O_Negative",
            "AB_Positive",
            "AB_Negative"
    ));

    @Transactional
    public Response<DTO> getInsights(){

        ArrayList<String> cities= repo.getDistinctCities();
        List<DTO> result=new ArrayList<>();
        LocalDate cutoffLocalDate = LocalDate.now().minusMonths(3);
        Date cutoffDate = Date.valueOf(cutoffLocalDate);
        for(String city: cities){
            for(String bloodType: bloodTypes){
                DTO data= new DTO();
                data.setCity(city);
                data.setBloodType(bloodType);
                data.setBloodTypeCount(repo.getCountOfBloodType(bloodType,city));
                data.setCanDonateCount(repo.getCountOfCanDonate(bloodType,city));
                data.setDiabetesCount(repo.getCountOfDiabetes(bloodType,city));
                data.setCancerCount(repo.getCountOfCancer(bloodType,city));
                data.setAidsCount(repo.getCountOfAids(bloodType,city));
                data.setCannotDonateCount(data.getBloodTypeCount()- data.getCanDonateCount());
                data.setMalariaCount(repo.getCountOfMalaria(bloodType,city));
                data.setHighPressureCount(repo.getCountOfHighPressure(bloodType,city));
                data.setLastDonationCount(repo.getCountOfLastTimeToDonate(bloodType,city,cutoffDate));
                data.setGeneticCount(repo.getCountOfGeneticBloodDiseases(bloodType,city));
                data.setHepatitis_BCount(repo.getCountOfhepatitis_B(bloodType,city));
                data.setSyphilisCount(repo.getCountOfSyphilis(bloodType,city));
                result.add(data);
            }
        }
        System.out.println(bloodTypes);
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"), result);

    }

    public Response<DiseaseLocation> getDonationLocation(DateReq dateReq){
        List<DiseaseLocation> result= new ArrayList<>();
        List<Object[]> locations= repo.getDonationLocation(dateReq.getFrom(),dateReq.getTo());
        for(Object[] location : locations){
            DiseaseLocation donationLocation= new DiseaseLocation();
            donationLocation.setLat((Double) location[0]);
            donationLocation.setLang((Double) location[1]);
            result.add(donationLocation);
        }
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"), result);

    }



}
