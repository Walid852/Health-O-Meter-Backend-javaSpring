package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.Test.Models.TypesTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TestFactory {
    Map<TypesTest,BaseTest> map;
    @Autowired
    public TestFactory(Set<BaseTest> testSet){
        createTest(testSet);
    }

    private void createTest(Set<BaseTest> testSet) {
        map=new HashMap<TypesTest,BaseTest>();
        testSet.forEach(X-> map.put(X.GetType(),X));
    }
    public BaseTest findStrategy(TypesTest typeTest) {
        System.out.println(map.get(typeTest));
        return map.get(typeTest);
    }

}
