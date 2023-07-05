package com.example.projectdeploy.Disease.Controller;

import com.example.projectdeploy.Disease.Models.Disease;
import com.example.projectdeploy.Disease.Services.FetchDiseasesService;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    Response<Disease> findAllDiseaseByName(@RequestParam String name){
        return fetchDiseasesService.findAllDiseaseByName(name);
    }
    @GetMapping(path="/findAllDiseaseByIsCured")
    public @ResponseBody
    Response<Disease> findAllDiseaseByIsCured(@RequestParam Boolean isCured){
        return fetchDiseasesService.findAllDiseaseByIsCured(isCured);
    }
    @GetMapping(path="/findDiseaseByName")
    public @ResponseBody
    Response<Disease> findDiseaseByName(@RequestParam String name,@RequestParam UUID id){
        return fetchDiseasesService.findDiseaseByName(name,id);
    }
    @GetMapping(path="/findDiseaseByIsCured")
    public @ResponseBody
    Response<Disease> findDiseaseByIsCured(@RequestParam Boolean isCured,@RequestParam UUID id){
        return fetchDiseasesService.findDiseaseByIsCured(isCured,id);
    }
    @GetMapping(path="/findDiseaseByMedicalInformationId")
    public @ResponseBody
    Response<Disease> findDiseaseByMedicalInformationId(@RequestParam UUID id){
        return fetchDiseasesService.findDiseaseByMedicalInformationId(id);
    }
    @GetMapping(path="/filterDiseaseByStartDate")
    public @ResponseBody
    Response<Disease> filterDiseaseByStartDate(@RequestParam UUID medicalInformationId,@RequestParam Date start,@RequestParam Date end){
        return fetchDiseasesService.filterDiseaseByStartDate(medicalInformationId,start,end);
    }
    @GetMapping(path="/filterDiseaseByEndDate")
    public @ResponseBody
    Response<Disease> filterDiseaseByEndDate(@RequestParam UUID medicalInformationId,@RequestParam Date start,@RequestParam Date end){
        return fetchDiseasesService.filterDiseaseByEndDate(medicalInformationId,start,end);
    }
    @GetMapping(path="/findDiseaseById")
    public @ResponseBody
    Response<Disease> findDiseaseById(@RequestParam UUID id){
        return fetchDiseasesService.findDiseaseById(id);
    }
}
