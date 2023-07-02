package com.example.projectdeploy.Doctors.DiseaseAnalysis.Controller;


import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.Data;
import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DateReq;
import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DiseaseLocation;
import com.example.projectdeploy.Doctors.DiseaseAnalysis.Service.DiAnalysisService;

import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiAnalysisController {

    @Autowired
    DiAnalysisService diAnalysisService;

    @PostMapping(path="/diInsights")
    public @ResponseBody
    Response<Data> getInsights(@RequestBody DateReq dateReq){
        return diAnalysisService.getInsights(dateReq);
    }

    @PostMapping(path="/diseaseLocation")
    public @ResponseBody
    Response<DiseaseLocation> getDiseaseLocation(@RequestBody DateReq dateReq){
        return diAnalysisService.getDiseaseLocation(dateReq);
    }

}
