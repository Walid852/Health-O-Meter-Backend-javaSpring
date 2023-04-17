package com.example.projectdeploy.MedicalInformation;

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
    MedicalInformation createMedicalInformation(@RequestBody CreateMedicalInformation createMedicalInformation){
        return crudServiceMedicalInformation.createMedicalInformation(createMedicalInformation);
    }
    @PatchMapping(path="/UpdateMedicalInformation")
    public @ResponseBody
    MedicalInformation UpdateMedicalInformation(@RequestBody UpdateMedicalInformation updateMedicalInformation){
        return crudServiceMedicalInformation.UpdateMedicalInformation(updateMedicalInformation);
    }
    @DeleteMapping(path="/DeleteMedicalInformation")
    public @ResponseBody
    String DeleteMedicalInformation(@RequestParam UUID MedicalInfoId){
        return crudServiceMedicalInformation.DeleteMedicalInformation(MedicalInfoId);
    }
    @GetMapping(path="/GetMedicalInformationById")
    public @ResponseBody
    MedicalInformation GetMedicalInformationById(@RequestParam UUID MedicalInfoId){
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
    MedicalInformation GetMedicalInformationByUserId(@RequestParam UUID UserId){
        return fetchServiceMedicalInformation.GetMedicalInformationByUserId(UserId);
    }
    @GetMapping(path="/GetMedicalInformationByMemberId")
    public @ResponseBody
    MedicalInformation GetMedicalInformationByMemberId(@RequestParam UUID MemberId){
        return fetchServiceMedicalInformation.GetMedicalInformationByMemberId(MemberId);
    }
    @GetMapping(path="/GetAllMedicalInformation")
    public @ResponseBody
    List<MedicalInformation> GetAllMedicalInformation(){
        return fetchServiceMedicalInformation.GetAllMedicalInformation();
    }
    @GetMapping(path="/GetAllMedicalInformationForHeight")
    public @ResponseBody
    List<MedicalInformation> GetAllMedicalInformationForHeight(@RequestParam int height){
        return fetchServiceMedicalInformation.GetAllMedicalInformationForHeight(height);
    }
    @GetMapping(path="/GetAllMedicalInformationForWeight")
    public @ResponseBody
    List<MedicalInformation> GetAllMedicalInformationForWeight(@RequestParam int weight){
        return fetchServiceMedicalInformation.GetAllMedicalInformationForWeight(weight);
    }
    @GetMapping(path="/GetAllMedicalInformationForNumOfCubs")
    public @ResponseBody    
    List<MedicalInformation> GetAllMedicalInformationForNumOfCubs(@RequestParam int numOfCup){
        return fetchServiceMedicalInformation.GetAllMedicalInformationForNumOfCubs(numOfCup);
    }
    @GetMapping(path="/findAllMedicalInformationForBloodType")
    public @ResponseBody
    List<MedicalInformation> findAllMedicalInformationForBloodType(@RequestParam BloodType bloodType){
        return fetchServiceMedicalInformation.findAllMedicalInformationForBloodType(bloodType);
    }



}
