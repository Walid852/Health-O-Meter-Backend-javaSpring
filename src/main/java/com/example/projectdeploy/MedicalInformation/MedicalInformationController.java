package com.example.projectdeploy.MedicalInformation;

import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/MedicalInformation")
public class MedicalInformationController {
 @Autowired
    CrudServiceMedicalInformation crudServiceMedicalInformation;
 @Autowired
    FetchServiceMedicalInformation fetchServiceMedicalInformation;
    @PostMapping(path="/createMedicalInformation")
    public @ResponseBody
    Response<MedicalInformation> createMedicalInformation(@RequestBody CreateMedicalInformation createMedicalInformation){
        return crudServiceMedicalInformation.createMedicalInformation(createMedicalInformation);
    }
    @PatchMapping(path="/UpdateMedicalInformation")
    public @ResponseBody
    Response<MedicalInformation> UpdateMedicalInformation(@RequestBody UpdateMedicalInformation updateMedicalInformation){
        return crudServiceMedicalInformation.UpdateMedicalInformation(updateMedicalInformation);
    }
    @DeleteMapping(path="/DeleteMedicalInformation")
    public @ResponseBody
    Response<MedicalInformation> DeleteMedicalInformation(@RequestParam UUID MedicalInfoId){
        return crudServiceMedicalInformation.DeleteMedicalInformation(MedicalInfoId);
    }
    @GetMapping(path="/GetMedicalInformationById")
    public @ResponseBody
    Response<MedicalInformation> GetMedicalInformationById(@RequestParam UUID MedicalInfoId){
        return fetchServiceMedicalInformation.GetMedicalInformationById(MedicalInfoId);
    }
    @GetMapping(path="/GivenAndReceiveForBloodType")
    public @ResponseBody
    List<BloodType> GivenAndReceiveForBloodType(@RequestParam BloodType bloodType){
        return crudServiceMedicalInformation.GivenAndReceiveForBloodType(bloodType);
    }
    @GetMapping(path="/ValidateToDonate")
    public @ResponseBody
    List<MedicalInformation> ValidateToDonate(@RequestParam BloodType bloodType,@RequestParam String government){
        return crudServiceMedicalInformation.ValidateToDonate(bloodType,government);
    }
    @GetMapping(path="/GetMedicalInformationByUserId")
    public @ResponseBody
    Response<MedicalInformation> GetMedicalInformationByUserId(@RequestParam UUID UserId){
        return fetchServiceMedicalInformation.GetMedicalInformationByUserId(UserId);
    }
    @GetMapping(path="/GetMedicalInformationByMemberId")
    public @ResponseBody
    Response<MedicalInformation> GetMedicalInformationByMemberId(@RequestParam UUID MemberId){
        return fetchServiceMedicalInformation.GetMedicalInformationByMemberId(MemberId);
    }
    @GetMapping(path="/GetAllMedicalInformation")
    public @ResponseBody
    Response<MedicalInformation> GetAllMedicalInformation(){
        return fetchServiceMedicalInformation.GetAllMedicalInformation();
    }
    @GetMapping(path="/GetAllMedicalInformationForHeight")
    public @ResponseBody
    Response<MedicalInformation> GetAllMedicalInformationForHeight(@RequestParam int height){
        return fetchServiceMedicalInformation.GetAllMedicalInformationForHeight(height);
    }
    @GetMapping(path="/GetAllMedicalInformationForWeight")
    public @ResponseBody
    Response<MedicalInformation> GetAllMedicalInformationForWeight(@RequestParam int weight){
        return fetchServiceMedicalInformation.GetAllMedicalInformationForWeight(weight);
    }
    @GetMapping(path="/findAllMedicalInformationForBloodType")
    public @ResponseBody
    Response<MedicalInformation> findAllMedicalInformationForBloodType(@RequestParam BloodType bloodType){
        return fetchServiceMedicalInformation.findAllMedicalInformationForBloodType(bloodType);
    }



}
