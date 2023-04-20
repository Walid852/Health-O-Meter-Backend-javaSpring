package com.example.projectdeploy.Vaccine.Controller;

import com.example.projectdeploy.Vaccine.Model.Vaccine;
import com.example.projectdeploy.Vaccine.Service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class VaccineController {

    @Autowired
    VaccineService vaccineService;

    @PostMapping(value = "/vaccine")
    public @ResponseBody List<Vaccine> generateVaccine(@RequestParam UUID memberId){
        return vaccineService.generateVaccine(memberId);
    }


    @DeleteMapping(value = "/deletevaccine")
    public @ResponseBody void deleteBabyVaccine(@RequestParam UUID memberId){
        vaccineService.deleteBabyVaccine(memberId);
    }
    @GetMapping(value = "/getVaccine")
    public @ResponseBody List<Vaccine> getBabyVaccine(@RequestParam UUID memberId){
        return vaccineService.getBabyVaccine(memberId);
    }
}
