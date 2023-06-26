package com.example.projectdeploy.Disease.Controller;

import com.example.projectdeploy.Disease.Models.Disease;
import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Services.FetchMedicalService;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/FetchMedicine")
public class FetchMedicineController {
    @Autowired
    FetchMedicalService fetchMedicalService;

    @GetMapping(path="/findMedicineById")
    public @ResponseBody
    Response<Medicine>  findMedicineById(@RequestParam UUID id){
        return fetchMedicalService.findMedicineById(id);
    }
    @GetMapping(path="/findMedicineByNameForMedicalInformationId")
    public @ResponseBody
    Response<Medicine> findMedicineByNameForMedicalInformationId(@RequestParam UUID id,@RequestParam String name){
        return fetchMedicalService.findMedicineByNameForMedicalInformationId(id,name);
    }
    @GetMapping(path="/findMedicineForMedicalInformationId")
    public @ResponseBody
    Response<Medicine>findMedicineForMedicalInformationId(@RequestParam UUID id){
        return fetchMedicalService.findMedicineForMedicalInformationId(id);
    }
    @GetMapping(path="/findMedicineForDisease")
    public @ResponseBody
    Response<Medicine> findMedicineForDisease(@RequestParam UUID id){
        return fetchMedicalService.findMedicineForDisease(id);
    }
    @GetMapping(path="/findMedicineByIsNotifiedForMedicalInformationId")
    public @ResponseBody
    Response<Medicine> findMedicineByIsNotifiedForMedicalInformationId(@RequestParam UUID id,@RequestParam Boolean isNotified){
        return fetchMedicalService.findMedicineByIsNotifiedForMedicalInformationId(id,isNotified);
    }


}
