package com.example.projectdeploy.Disease.Controller;

import com.example.projectdeploy.Disease.DTO.DiseaseDto;
import com.example.projectdeploy.Disease.DTO.MedicineDto;
import com.example.projectdeploy.Disease.Models.Disease;
import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Services.CrudDiseasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/Disease")
public class CrudDiseaseController {
    @Autowired
    CrudDiseasesService crudDiseasesService;

    @PostMapping(path="/AddDisease")
    public @ResponseBody
    Disease AddDisease(@RequestBody DiseaseDto creationDisease){
        return crudDiseasesService.AddDisease(creationDisease);
    }
    @PutMapping(path="/UpdateDisease")
    public @ResponseBody
    Disease UpdateDisease(DiseaseDto creationDisease){
        return crudDiseasesService.UpdateDisease(creationDisease);
    }
    @PostMapping(path="/AddMedicine")
    public @ResponseBody
    Medicine AddMedicine(@RequestBody MedicineDto creationMedicine){
        return crudDiseasesService.AddMedicine(creationMedicine);
    }
    @PutMapping(path="/UpdateMedicine")
    public @ResponseBody
    Medicine UpdateMedicine(MedicineDto creationMedicine){
        return crudDiseasesService.UpdateMedicine(creationMedicine);
    }
    @DeleteMapping (path="/DeleteDisease")
    public @ResponseBody
    String DeleteDisease(UUID diseaseId){
        return crudDiseasesService.DeleteDisease(diseaseId);
    }
    @DeleteMapping (path="/DeleteMedicine")
    public @ResponseBody
    String DeleteMedicine(UUID medicineId){
        return crudDiseasesService.DeleteMedicine(medicineId);
    }
    @DeleteMapping (path="/DeleteMedicineTimes")
    public @ResponseBody
    String DeleteMedicineTimes(UUID medicineId){
        return crudDiseasesService.DeleteMedicineTimes(medicineId);
    }

}
