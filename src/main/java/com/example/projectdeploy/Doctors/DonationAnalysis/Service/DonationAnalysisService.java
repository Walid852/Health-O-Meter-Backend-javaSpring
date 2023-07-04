package com.example.projectdeploy.Doctors.DonationAnalysis.Service;

import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DateReq;
import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DiseaseLocation;
import com.example.projectdeploy.Doctors.DonationAnalysis.DTO.DTO;
import com.example.projectdeploy.Doctors.DonationAnalysis.DTO.DistinctCities;
import com.example.projectdeploy.Doctors.DonationAnalysis.Repo.Repo;
import com.example.projectdeploy.MedicalInformation.BloodType;
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



    List<BloodType> bloodTypes = new ArrayList<>(Arrays.asList(
            BloodType.A_Positive,
            BloodType.A_Negative,
            BloodType.B_Negative,
            BloodType.B_Positive,
            BloodType.O_Negative,
            BloodType.O_Positive,
            BloodType.AB_Positive,
            BloodType.AB_Negative
    ));

    public Response<DistinctCities> getDistinctCities(){
        ArrayList<DistinctCities> distinctCities= new ArrayList<>();
        ArrayList<String> cities=repo.getDistinctCities();
        for(String city:cities){
            DistinctCities distCity= new DistinctCities();
            distCity.setCity(city);
            distinctCities.add(distCity);
        }
        return new Response<>(true, StaticsText.MessageForTest("Distinct cities", "Returned"), distinctCities);
    }

    @Transactional
    public Response<DTO> getInsights(String city,BloodType bloodType){

        ArrayList<String> cities= repo.getDistinctCities();
        List<DTO> result=new ArrayList<>();
        LocalDate cutoffLocalDate = LocalDate.now().minusMonths(3);
        Date cutoffDate = Date.valueOf(cutoffLocalDate);
        if(cities.contains(city) && bloodTypes.contains(bloodType)){
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
        }else{
            return new Response<>(false, StaticsText.MessageForTest("City or blood type", "is not found"), result);
        }
        System.out.println(bloodTypes);
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"), result);

    }

    public Response<DiseaseLocation> getDonationLocation(){
        List<DiseaseLocation> result= new ArrayList<>();
        List<Object[]> locations= repo.getDonationLocation();
        for(Object[] location : locations){
            DiseaseLocation donationLocation= new DiseaseLocation();
            donationLocation.setLat((Double) location[0]);
            donationLocation.setLang((Double) location[1]);
            result.add(donationLocation);
        }
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"), result);

    }



}
