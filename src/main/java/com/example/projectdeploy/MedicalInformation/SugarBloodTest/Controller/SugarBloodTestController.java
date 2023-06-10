package com.example.projectdeploy.MedicalInformation.SugarBloodTest.Controller;

import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Model.SugarBloodTest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Requset.SugarTestRequest;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.Service.SugarBloodTestService;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.SugarAnalysis.CategoryAnalysis;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.SugarAnalysis.SugarAnalysis;
import com.example.projectdeploy.MedicalInformation.SugarBloodTest.TestPeriod;
import com.example.projectdeploy.Shared.Response;
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
    Response<SugarBloodTest> addRead(@RequestBody SugarTestRequest testRequest) {
        return testService.addRead(testRequest);
    }

    @PatchMapping(value = "/updateRead")
    public @ResponseBody
    Response<SugarBloodTest> updateRead(@RequestBody SugarTestRequest testRequest) {
        return testService.updateRead(testRequest);
    }

    @DeleteMapping(value = "/deleteRead")
    public Response<SugarBloodTest> deleteRead(@RequestParam UUID id) {
        return testService.deleteRead(id);
    }

    @GetMapping(value = "/getSugarBloodTestByMedicalInformationId")
    public @ResponseBody
    Response<SugarBloodTest> getSugarBloodTestByMedicalInformationId(@RequestParam UUID medicalInformationId) {
        return testService.getSugarBloodTestByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getDeletedSugarBloodTestByMedicalInformationId")
    public @ResponseBody
    Response<SugarBloodTest> getDeletedSugarBloodTestByMedicalInformationId(@RequestParam UUID medicalInformationId){
        return testService.getDeletedSugarBloodTestByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getAllSugarBloodTest")
    public @ResponseBody
    Response<SugarBloodTest> getAllSugarBloodTest(){
        return testService.getAllSugarBloodTest();
    }
    @GetMapping(value = "/getSugarBloodTestById")
    public @ResponseBody
    Response<SugarBloodTest> getSugarBloodTestById(@RequestParam UUID id){
        return testService.getSugarBloodTestById(id);
    }
    @GetMapping(value = "/getSugarBloodTestByTestPeriod")
    public @ResponseBody
    Response<SugarBloodTest> getSugarBloodTestByTestPeriod(@RequestParam TestPeriod testPeriod){
        return testService.getSugarBloodTestByTestPeriod(testPeriod);
    }
    @GetMapping(value = "/getSugarBloodTestByRead")
    public @ResponseBody
    Response<SugarBloodTest> getSugarBloodTestByRead(@RequestParam int read){
        return testService.getSugarBloodTestByRead(read);
    }
    @GetMapping(value = "/filterTestByDateUser")
    public @ResponseBody
    Response<SugarBloodTest> filterTestByDateUser(@RequestParam UUID medicalInformationId,@RequestParam Date start,@RequestParam Date end){
        return testService.filterTestByDateUser(medicalInformationId,start,end);
    }

    @GetMapping(value = "/sugarAnalysis")
    public @ResponseBody Response<SugarAnalysis> SugarAnalysis(@RequestParam UUID medicalId){
        return testService.SugarAnalysis(medicalId);
    }

    @GetMapping(value = "/sCategoryAnalysis")
    public @ResponseBody Response<CategoryAnalysis> sCategoryAnalysis(@RequestParam UUID medicalId){
        return testService.sCategoryAnalysis(medicalId);
    }
}

