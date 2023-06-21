package com.example.projectdeploy.Test.Services;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.Test.Models.LiverFunction;
import com.example.projectdeploy.Test.Models.Test;
import com.example.projectdeploy.Test.Models.TypesTest;
import com.example.projectdeploy.Test.Repos.LiverFunctionRepo;
import com.example.projectdeploy.Test.TestRequests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class LiverFunctionTest implements BaseTest{
    @Autowired
    LiverFunctionRepo liverFunctionRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Transactional
    @Override
    public Response<Test> AddTest(Request test) {
        try {
            MedicalInformation medicalInformation = medicalInformationRepo.findMedicalInformationById(test.getMedicalInformation());
            if (medicalInformation == null) return new Response<>(false, StaticsText.MessageForTest("liverFunction Test", "not Found"));
            LiverFunction liverFunction = new LiverFunction(
                    medicalInformation, test.getIsDeleted(), test.getDate(), test.getTest(),
                    test.getGammaGT(), test.getBilirubin_Total(), test.getBilirubin_Direct(),
                    test.getAST(), test.getALT(), test.getAlk(), test.getTotalProtein(), test.getAlbumin()
            );
            liverFunctionRepo.saveAndFlush(liverFunction);
            List<Test> result = new ArrayList<>();
            result.add(liverFunction);
            return new Response<>(true, StaticsText.MessageForTest("liverFunction Test", "Created"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> UpdateTest(Request test) {
        try {
            LiverFunction liverFunction = liverFunctionRepo.liverFunctionById(test.getId());
            if (liverFunction == null) return new Response<>(false, StaticsText.MessageForTest("liverFunction Test", "not Found"));
            if (test.getAlbumin() != -1) liverFunction.setAlbumin(test.getAlbumin());
            if (test.getAlk() != -1) liverFunction.setAlk(test.getAlk());
            if (test.getALT() != -1) liverFunction.setALT(test.getALT());
            if (test.getAST() != -1) liverFunction.setAST(test.getAST());
            if (test.getBilirubin_Direct() != -1) liverFunction.setBilirubin_Direct(test.getBilirubin_Direct());
            if (test.getBilirubin_Total() != -1) liverFunction.setBilirubin_Total(test.getBilirubin_Total());
            if (test.getGammaGT() != -1) liverFunction.setGammaGT(test.getGammaGT());
            if (test.getTotalProtein() != -1) liverFunction.setTotalProtein(test.getTotalProtein());
            if(test.getDate()!=null){liverFunction.setDate(test.getDate());}
            liverFunctionRepo.saveAndFlush(liverFunction);
            List<Test> result = new ArrayList<>();
            result.add(liverFunction);
            return new Response<>(true, StaticsText.MessageForTest("liverFunction Test", "Updated"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> DeleteTest(UUID id) {
        try {
            LiverFunction liverFunction = liverFunctionRepo.liverFunctionById(id);
            if (liverFunction == null) return new Response<>(false, StaticsText.MessageForTest("liverFunction Test", "not Found"));
            liverFunction.setIsDeleted(true);
            liverFunctionRepo.saveAndFlush(liverFunction);
            List<Test> result = new ArrayList<>();
            result.add(liverFunction);
            return new Response<>(true, StaticsText.MessageForTest("liverFunction Test", "deleted"),result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> RetrieveAllTest() {
        try {
            List<Test> result=liverFunctionRepo.getAllTestForSpecificType();
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("liverFunction Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("liverFunction Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> RetrieveAllTestByMedicalInformationId(UUID id) {
        try {
            List<Test> result=liverFunctionRepo.liverFunctionByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("liverFunction Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("liverFunction Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> testDeletedByMedicalInformationId(UUID id) {
        try {
            List<Test> result=liverFunctionRepo.liverFunctionDeletedByMedicalInformationId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("liverFunction Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("liverFunction Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public Response<Test> TestByDate(Date startDate,Date endDate) {
        try {
            List<Test> result=liverFunctionRepo.TestByDate(startDate,endDate);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("liverFunction Test", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("liverFunction Test", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    @Transactional
    @Override
    public TypesTest GetType() {
        return TypesTest.LiverFunction;
    }
}
