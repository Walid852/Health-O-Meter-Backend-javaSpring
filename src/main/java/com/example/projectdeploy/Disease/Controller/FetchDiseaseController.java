package com.example.projectdeploy.Disease.Controller;

import com.example.projectdeploy.Disease.Models.Disease;
import com.example.projectdeploy.Disease.Services.FetchDiseasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/FetchDisease")
public class FetchDiseaseController {
    @Autowired
    FetchDiseasesService fetchDiseasesService;


    @GetMapping(path="/findAllDiseaseByName")
    public @ResponseBody
    List<Disease> findAllDiseaseByName(String name){
        return fetchDiseasesService.findAllDiseaseByName(name);
    }
    @GetMapping(path="/findAllDiseaseByIsCured")
    public @ResponseBody
    List<Disease> findAllDiseaseByIsCured(Boolean isCured){
        return fetchDiseasesService.findAllDiseaseByIsCured(isCured);
    }
    @GetMapping(path="/findDiseaseByName")
    public @ResponseBody
    List<Disease> findDiseaseByName(String name, UUID id){
        return fetchDiseasesService.findDiseaseByName(name,id);
    }
    @GetMapping(path="/findDiseaseByIsCured")
    public @ResponseBody
    List<Disease> findDiseaseByIsCured(Boolean isCured,UUID id){
        return fetchDiseasesService.findDiseaseByIsCured(isCured,id);
    }
    @GetMapping(path="/findDiseaseByMedicalInformationId")
    public @ResponseBody
    List<Disease> findDiseaseByMedicalInformationId(UUID id){
        return fetchDiseasesService.findDiseaseByMedicalInformationId(id);
    }
    @GetMapping(path="/filterDiseaseByStartDate")
    public @ResponseBody
    List<Disease> filterDiseaseByStartDate(UUID medicalInformationId, Date start, Date end){
        return fetchDiseasesService.filterDiseaseByStartDate(medicalInformationId,start,end);
    }
    @GetMapping(path="/filterDiseaseByEndDate")
    public @ResponseBody
    List<Disease> filterDiseaseByEndDate(UUID medicalInformationId, Date start, Date end){
        return fetchDiseasesService.filterDiseaseByEndDate(medicalInformationId,start,end);
    }

}
