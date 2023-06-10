package com.example.projectdeploy.Disease.Controller;

import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Models.MedicineTime;
import com.example.projectdeploy.Disease.Services.FetchMedicalTimesService;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    Response<MedicineTime>  findMedicineTimeById(UUID id) {
        return fetchMedicalTimesService.findMedicineTimeById(id);
    }

    @GetMapping(path = "/findMedicineTimeByMedicineId")
    public @ResponseBody
    Response<MedicineTime>  findMedicineTimeByMedicineId(UUID id) {
        return fetchMedicalTimesService.findMedicineTimeByMedicineId(id);
    }

    @GetMapping(path = "/findMedicineTimeByDiseaseId")
    public @ResponseBody
    Response<MedicineTime>  findMedicineTimeByDiseaseId(UUID id) {
        return fetchMedicalTimesService.findMedicineTimeByDiseaseId(id);
    }

    @GetMapping(path = "/findMedicineTimeByMedicalInformationIdAndNotified")
    public @ResponseBody
    Response<MedicineTime>  findMedicineTimeByMedicalInformationIdAndNotified(UUID id) {
        return fetchMedicalTimesService.findMedicineTimeByMedicalInformationIdAndNotified(id);
    }
}
