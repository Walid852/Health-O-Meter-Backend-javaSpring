package com.example.projectdeploy.Disease.Controller;

import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Services.FetchMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    Medicine findMedicineById(UUID id){
        return fetchMedicalService.findMedicineById(id);
    }
    @GetMapping(path="/findMedicineByNameForMedicalInformationId")
    public @ResponseBody
    List<Medicine> findMedicineByNameForMedicalInformationId(UUID id, String name){
        return fetchMedicalService.findMedicineByNameForMedicalInformationId(id,name);
    }
    @GetMapping(path="/findMedicineForMedicalInformationId")
    public @ResponseBody
    List<Medicine> findMedicineForMedicalInformationId(UUID id){
        return fetchMedicalService.findMedicineForMedicalInformationId(id);
    }
    @GetMapping(path="/findMedicineForDisease")
    public @ResponseBody
    List<Medicine> findMedicineForDisease(UUID id){
        return fetchMedicalService.findMedicineForDisease(id);
    }
    @GetMapping(path="/findMedicineByIsNotifiedForMedicalInformationId")
    public @ResponseBody
    List<Medicine> findMedicineByIsNotifiedForMedicalInformationId(UUID id,Boolean isNotified){
        return fetchMedicalService.findMedicineByIsNotifiedForMedicalInformationId(id,isNotified);
    }


}
