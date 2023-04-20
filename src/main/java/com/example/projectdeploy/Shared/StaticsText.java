package com.example.projectdeploy.Shared;

public class StaticsText {
    public static String MessageForTest(String testName,String Status){
        return testName+" "+Status;
    }
    public static String MessageForTestError(){
        return "Unexpected Server Error please try again";
    }

}
