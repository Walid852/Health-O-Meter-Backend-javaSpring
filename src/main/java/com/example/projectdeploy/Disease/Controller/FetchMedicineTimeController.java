package com.example.projectdeploy.Disease.Controller;

import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Models.MedicineTime;
import com.example.projectdeploy.Disease.Services.FetchMedicalTimesService;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/FetchMedicineTime")
public class FetchMedicineTimeController {
    @Autowired
    FetchMedicalTimesService fetchMedicalTimesService;

    @GetMapping(path = "/findMedicineTimeById")
    public @ResponseBody
    Response<MedicineTime>  findMedicineTimeById(@RequestParam UUID id) {
        return fetchMedicalTimesService.findMedicineTimeById(id);
    }

    @GetMapping(path = "/findMedicineTimeByMedicineId")
    public @ResponseBody
    Response<MedicineTime>  findMedicineTimeByMedicineId(@RequestParam UUID id) {
        return fetchMedicalTimesService.findMedicineTimeByMedicineId(id);
    }

    @GetMapping(path = "/findMedicineTimeByDiseaseId")
    public @ResponseBody
    Response<MedicineTime>  findMedicineTimeByDiseaseId(@RequestParam UUID id) {
        return fetchMedicalTimesService.findMedicineTimeByDiseaseId(id);
    }

    @GetMapping(path = "/findMedicineTimeByMedicalInformationIdAndNotified")
    public @ResponseBody
    Response<MedicineTime>  findMedicineTimeByMedicalInformationIdAndNotified(@RequestParam UUID id) {
        return fetchMedicalTimesService.findMedicineTimeByMedicalInformationIdAndNotified(id);
    }
}
