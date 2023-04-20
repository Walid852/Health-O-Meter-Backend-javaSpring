package com.example.projectdeploy.Test.Services;

import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public interface BaseTest {
    Response<Test> AddTest(Request test);
    Response<Test> UpdateTest(Request test);
    Response<Test> DeleteTest(UUID id);
    Response<Test> RetrieveAllTest();
    Response<Test> RetrieveAllTestByMedicalInformationId(UUID id);
    Response<Test> testDeletedByMedicalInformationId(UUID id);
    Response<Test> TestByDate(Date startDate,Date endDate);
    TypesTest GetType();
}
