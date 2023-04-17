package com.example.projectdeploy.MedicalInformation.BloodPressure.Controller;

import com.example.projectdeploy.MedicalInformation.BloodPressure.Model.BloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.Service.CrudServiceBloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.Service.FetchServiceBloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.BloodPressureCategory;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.CreateBloodPressure;
import com.example.projectdeploy.MedicalInformation.BloodPressure.dto.UpdateBloodPressure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/BloodPressure")
public class BloodPressureController {
    @Autowired
    CrudServiceBloodPressure crudServiceBloodPressure;
    @Autowired
    FetchServiceBloodPressure fetchServiceBloodPressure;
    @PostMapping(path="/AddBloodPressure")
    public @ResponseBody
    BloodPressure AddBloodPressure(@RequestBody CreateBloodPressure createBloodPressure){
        return crudServiceBloodPressure.AddBloodPressure(createBloodPressure);
    }
    @PatchMapping(path="/UpdateBloodPressure")
    public @ResponseBody
    BloodPressure UpdateBloodPressure(@RequestBody UpdateBloodPressure updateBloodPressure){
        return crudServiceBloodPressure.UpdateBloodPressure(updateBloodPressure);
    }
    @DeleteMapping(path="/DeleteBloodPressure")
    public @ResponseBody
    String DeleteBloodPressure(@RequestParam UUID bloodPressureId){
        return crudServiceBloodPressure.DeleteBloodPressure(bloodPressureId);
    }
    @GetMapping(path="/GetBloodPressureById")
    public @ResponseBody
    BloodPressure GetBloodPressureById(@RequestParam UUID id){
        return fetchServiceBloodPressure.GetBloodPressureById(id);
    }
    @GetMapping(path="/GetBloodPressureByMedicalInformationId")
    public @ResponseBody
    List<BloodPressure> GetBloodPressureByMedicalInformationId(@RequestParam UUID MedicalInformationId){
        return  fetchServiceBloodPressure.GetBloodPressureByMedicalInformationId(MedicalInformationId);
    }
    @GetMapping(path="/findBloodPressureDeleted")
    public @ResponseBody
    List<BloodPressure> findBloodPressureDeleted(@RequestParam UUID MedicalInformationId){
        return  fetchServiceBloodPressure.findBloodPressureDeleted(MedicalInformationId);
    }

    @GetMapping(path="/GetAllBloodPressure")
    public @ResponseBody
    List<BloodPressure> GetAllBloodPressure(){
        return fetchServiceBloodPressure.GetAllBloodPressure();
    }
    @GetMapping(path="/GetAllBloodPressureByDate")
    public @ResponseBody
    List<BloodPressure> GetAllBloodPressureByDate(@RequestParam Date start, @RequestParam Date endDate){
        return fetchServiceBloodPressure.GetAllBloodPressureByDate(start,endDate);
    }
    @GetMapping(path="/GetBloodPressureByDiastolic")
    public @ResponseBody
    List<BloodPressure> GetBloodPressureByDiastolic(@RequestParam int diastolic){
        return fetchServiceBloodPressure.GetBloodPressureByDiastolic(diastolic);
    }
    @GetMapping(path="/GetBloodPressureBySystolic")
    public @ResponseBody
    List<BloodPressure> GetBloodPressureBySystolic(@RequestParam int systolic){
        return fetchServiceBloodPressure.GetBloodPressureBySystolic(systolic);
    }
    @GetMapping(path="/GetBloodPressureByBloodPressureCategory")
    public @ResponseBody
    List<BloodPressure> GetBloodPressureByBloodPressureCategory(@RequestParam BloodPressureCategory bloodPressureCategory){
        return fetchServiceBloodPressure.GetBloodPressureByBloodPressureCategory(bloodPressureCategory);
    }




}
