package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Controller;

import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model.SugarBloodTest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Requset.SugarTestRequest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Service.SugarBloodTestService;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/SugarBloodTest")
public class SugarBloodTestController {
    @Autowired
    SugarBloodTestService testService;

    @PostMapping(value = "/addRead")
    public @ResponseBody
    SugarBloodTest addRead(@RequestBody SugarTestRequest testRequest) {
        return testService.addRead(testRequest);
    }

    @PutMapping(value = "/updateRead")
    public @ResponseBody
    SugarBloodTest updateRead(@RequestBody SugarTestRequest testRequest) {
        return testService.updateRead(testRequest);
    }

    @DeleteMapping(value = "/deleteRead")
    public String deleteRead(@RequestParam UUID id) {
        return testService.deleteRead(id);
    }

    @GetMapping(value = "/getSugarBloodTestByMedicalInformationId")
    public @ResponseBody
    List<SugarBloodTest> getSugarBloodTestByMedicalInformationId(@RequestParam UUID medicalInformationId) {
        return testService.getSugarBloodTestByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getDeletedSugarBloodTestByMedicalInformationId")
    public @ResponseBody
    List<SugarBloodTest> getDeletedSugarBloodTestByMedicalInformationId(@RequestParam UUID medicalInformationId){
        return testService.getDeletedSugarBloodTestByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getAllSugarBloodTest")
    public @ResponseBody
    List<SugarBloodTest> getAllSugarBloodTest(){
        return testService.getAllSugarBloodTest();
    }
    @GetMapping(value = "/getSugarBloodTestById")
    public @ResponseBody
    SugarBloodTest getSugarBloodTestById(@RequestParam UUID id){
        return testService.getSugarBloodTestById(id);
    }
    @GetMapping(value = "/getSugarBloodTestByTestPeriod")
    public @ResponseBody
    List<SugarBloodTest> getSugarBloodTestByTestPeriod(@RequestParam TestPeriod testPeriod){
        return testService.getSugarBloodTestByTestPeriod(testPeriod);
    }
    @GetMapping(value = "/getSugarBloodTestByRead")
    public @ResponseBody
    List<SugarBloodTest> getSugarBloodTestByRead(@RequestParam int read){
        return testService.getSugarBloodTestByRead(read);
    }
    @GetMapping(value = "/filterTestByDateUser")
    public @ResponseBody
    List<SugarBloodTest> filterTestByDateUser(@RequestParam UUID medicalInformationId,@RequestParam Date start,@RequestParam Date end){
        return testService.filterTestByDateUser(medicalInformationId,start,end);
    }
}

