package com.example.projectdeploy.Test.Controller;

import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Services.BaseTest;
import com.example.projectdeploy.Test.Services.TestFactory;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/Test")
public class TestController {
    @Autowired
    TestFactory testFactory;
    @PostMapping(path="/createTest")
    public @ResponseBody
    Response<Test> AddTest(@RequestBody  Request test){
        BaseTest strategy =testFactory.findStrategy(test.getTest());
        return strategy.AddTest(test);
    }
    @PatchMapping(path="/UpdateTest")
    public @ResponseBody
    Response<Test> UpdateTest(@RequestBody Request test){
        BaseTest strategy =testFactory.findStrategy(test.getTest());
        if (strategy==null)return null;
        return strategy.UpdateTest(test);
    }
    @DeleteMapping(path="/DeleteTest")
    public @ResponseBody
    Response<Test> DeleteTest(@RequestParam UUID id,@RequestParam TypesTest typeTest){
        BaseTest strategy =testFactory.findStrategy(typeTest);
        return strategy.DeleteTest(id);
    }
    @GetMapping(path="/GetTestByMedicalInformationId")
    public @ResponseBody
    Response<Test> RetrieveAllTestByMedicalInformationId(@RequestParam UUID id,@RequestParam TypesTest typeTest){
        BaseTest strategy =testFactory.findStrategy(typeTest);
        return strategy.RetrieveAllTestByMedicalInformationId(id);
    }
    @GetMapping(path="/GetAllTestsForSpecificTest")
    public @ResponseBody
    Response<Test> RetrieveAllTest(@RequestParam TypesTest typeTest){
        BaseTest strategy =testFactory.findStrategy(typeTest);
        return strategy.RetrieveAllTest();
    }
    @GetMapping(path="/TestByDate")
    public @ResponseBody
    Response<Test> TestByDate(@RequestParam Date startDate,@RequestParam Date endDate,@RequestParam TypesTest typeTest){
        BaseTest strategy =testFactory.findStrategy(typeTest);
        return strategy.TestByDate(startDate,endDate);
    }

}
