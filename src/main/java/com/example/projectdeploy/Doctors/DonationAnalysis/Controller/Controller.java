package com.example.projectdeploy.Doctors.DonationAnalysis.Controller;


import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DateReq;
import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DiseaseLocation;
import com.example.projectdeploy.Doctors.DonationAnalysis.DTO.DTO;
import com.example.projectdeploy.Doctors.DonationAnalysis.DTO.DistinctCities;
import com.example.projectdeploy.Doctors.DonationAnalysis.Service.DonationAnalysisService;
import com.example.projectdeploy.MedicalInformation.BloodType;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Services.BaseTest;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;


@RestController
public class Controller {

    @Autowired
    DonationAnalysisService donationAnalysisService;

    @GetMapping(path = "/getDistinctCities")
    public @ResponseBody
    Response<DistinctCities> getDistinctCities(){
        return donationAnalysisService.getDistinctCities();
    }

    @GetMapping(path="/getInsights")
    public @ResponseBody
    Response<DTO> getInsights(@RequestParam String city, @RequestParam BloodType bloodType){
        return donationAnalysisService.getInsights(city, bloodType);
    }

    @GetMapping(path="/donationLocation") public @ResponseBody Response<DiseaseLocation>
    getDonationLocation(@RequestParam String city){
        return donationAnalysisService.getDonationLocation(city);
    }
}
