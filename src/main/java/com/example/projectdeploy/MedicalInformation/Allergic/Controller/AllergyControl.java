package com.example.projectdeploy.MedicalInformation.Allergic.Controller;

import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.MedicalInformation.Allergic.Request.AllergyRequest;
import com.example.projectdeploy.MedicalInformation.Allergic.Service.AllergyService;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/Allergy")
public class AllergyControl {
    @Autowired
    AllergyService allergyService;

    @PostMapping(value = "/addAllergy")
    public @ResponseBody
    Response<Allergy> addAllergy(@RequestBody AllergyRequest allergyRequest){
        return allergyService.addAllergy(allergyRequest);
    }

    @PutMapping(value = "/updateAllergy")
    public @ResponseBody
    Response<Allergy> updateAllergy(@RequestBody AllergyRequest allergyRequest){
        return allergyService.updateAllergy(allergyRequest);
    }
    @DeleteMapping(value = "/deleteAllergy")
    public @ResponseBody
    Response<Allergy> deleteAllergy(@RequestParam UUID id){
        return allergyService.deleteAllergy(id);
    }

    @DeleteMapping(value = "/getAllergyByMedicalInformationId")
    public @ResponseBody
    Response<Allergy> getAllergyByMedicalInformationId(@RequestParam UUID medicalInformationId){
        return allergyService.getAllergyByMedicalInformationId(medicalInformationId);
    }

    @GetMapping(value = "/getAllergyDeletedByMedicalInformationId")
    public @ResponseBody
    Response<Allergy> getAllergyDeletedByMedicalInformationId(@RequestParam UUID medicalInformationId){
        return allergyService.getAllergyDeletedByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getAllAllergy")
    public @ResponseBody
    Response<Allergy> getAllAllergy(){
        return allergyService.getAllAllergy();
    }
    @GetMapping(value = "/getAllergyByName")
    public @ResponseBody
    Response<Allergy> getAllergyByName(@RequestParam String name){
        return allergyService.getAllergyByName(name);
    }
    @GetMapping(value = "/getAllergyByDate")
    public @ResponseBody
    Response<Allergy> getAllergyByDate(@RequestParam Date date){
        return allergyService.getAllergyByDate(date);
    }

    @GetMapping(value = "/getAllergyById")
    public @ResponseBody
    Response<Allergy> getAllergyById(@RequestParam UUID id){
        return allergyService.getAllergyById(id);
    }




}
