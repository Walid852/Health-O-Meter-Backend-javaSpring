package com.example.projectdeploy.Test.Controller;

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
    Test AddTest(@RequestBody  Request test){
        BaseTest strategy =testFactory.findStrategy(test.getTest());
        return strategy.AddTest(test);
    }
    @PatchMapping(path="/UpdateTest")
    public @ResponseBody
    Test UpdateTest(@RequestBody Request test){
        BaseTest strategy =testFactory.findStrategy(test.getTest());
        if (strategy==null)return null;
        return strategy.UpdateTest(test);
    }
    @DeleteMapping(path="/DeleteTest")
    public @ResponseBody
    String DeleteTest(@RequestParam UUID id,@RequestParam TypesTest typeTest){
        BaseTest strategy =testFactory.findStrategy(typeTest);
        return strategy.DeleteTest(id);
    }
    @GetMapping(path="/GetTestByMedicalInformationId")
    public @ResponseBody
    List<Test> RetrieveAllTestByMedicalInformationId(@RequestParam UUID id,@RequestParam TypesTest typeTest){
        BaseTest strategy =testFactory.findStrategy(typeTest);
        return strategy.RetrieveAllTestByMedicalInformationId(id);
    }
    @GetMapping(path="/GetAllTestsForSpecificTest")
    public @ResponseBody
    List<Test> RetrieveAllTest(@RequestParam TypesTest typeTest){
        BaseTest strategy =testFactory.findStrategy(typeTest);
        return strategy.RetrieveAllTest();
    }
    @GetMapping(path="/TestByDate")
    public @ResponseBody
    List<Test> TestByDate(@RequestParam Date date,@RequestParam TypesTest typeTest){
        BaseTest strategy =testFactory.findStrategy(typeTest);
        return strategy.TestByDate(date);
    }

}
