package com.example.projectdeploy.Doctors.DonationAnalysis.Controller;


import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DateReq;
import com.example.projectdeploy.Doctors.DonationAnalysis.DTO.DTO;
import com.example.projectdeploy.Doctors.DonationAnalysis.Service.DonationAnalysisService;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Services.BaseTest;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    DonationAnalysisService donationAnalysisService;

    @GetMapping(path="/getInsights")
    public @ResponseBody
    Response<DTO> getInsights(){
        return donationAnalysisService.getInsights();
    }


}
