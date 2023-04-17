package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public interface BaseTest {
    Test AddTest(Request test);
    Test UpdateTest(Request test);
    String DeleteTest(UUID id);
    List<Test> RetrieveAllTest();
    List<Test> RetrieveAllTestByMedicalInformationId(UUID id);
    List<Test> testDeletedByMedicalInformationId(UUID id);
    List<Test> TestByDate(Date date);
    TypesTest GetType();
}
