package com.example.projectdeploy.MedicalInformation.Surgery.Controller;

import com.example.projectdeploy.MedicalInformation.Surgery.Model.Surgery;
import com.example.projectdeploy.MedicalInformation.Surgery.Service.CrudServiceSurgery;
import com.example.projectdeploy.MedicalInformation.Surgery.Service.FetchServiceSurgery;
import com.example.projectdeploy.MedicalInformation.Surgery.dto.CreateSurgery;
import com.example.projectdeploy.MedicalInformation.Surgery.dto.UpdateSurgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@ResponseBody
@RequestMapping("/Surgery")
@RestController
public class SurgeryController {
    @Autowired
    CrudServiceSurgery crudServiceSurgery;
    @Autowired
    FetchServiceSurgery fetchServiceSurgery;
    @PostMapping(path="/AddSurgery")
    public @ResponseBody
    Surgery AddSurgery(@RequestBody CreateSurgery createSurgery){
        return crudServiceSurgery.AddSurgery(createSurgery);
    }
    @PatchMapping(path="/UpdateSurgery")
    public @ResponseBody
    Surgery UpdateSurgery(@RequestBody UpdateSurgery updateSurgery){
        return crudServiceSurgery.UpdateSurgery(updateSurgery);
    }
    @DeleteMapping(path="/DeleteSurgery")
    public @ResponseBody
    String DeleteSurgery(@RequestParam UUID surgeryId){
        return crudServiceSurgery.DeleteSurgery(surgeryId);
    }
    @GetMapping(path="/findSurgeryById")
    public @ResponseBody
    Surgery findSurgeryById(@RequestParam UUID id){
        return fetchServiceSurgery.findSurgeryById(id);
    }
    @GetMapping(path="/findAllSurgery")
    public @ResponseBody
    List<Surgery> findAllSurgery(){
        return fetchServiceSurgery.findAllSurgery();
    }
    @GetMapping(path="/findSurgeryByMedicalInformationId")
    public @ResponseBody
    List<Surgery> findSurgeryByMedicalInformationId(@RequestParam UUID id){
        return fetchServiceSurgery.findSurgeryByMedicalInformationId(id);
    }
    @GetMapping(path="/findSurgeryDeleted")
    public @ResponseBody
    List<Surgery> findSurgeryDeleted(@RequestParam UUID id){
        return fetchServiceSurgery.findSurgeryDeleted(id);
    }
    @GetMapping(path="/findSurgeryByName")
    public @ResponseBody
    List<Surgery> findSurgeryByName(@RequestParam String name){
        return fetchServiceSurgery.findSurgeryByName(name);
    }
    /*@GetMapping(path="/findSurgeryByBodyMember")
    public @ResponseBody
    List<Surgery> findSurgeryByBodyMember(@RequestParam String bodyMember){
        return fetchServiceSurgery.findSurgeryByBodyMember(bodyMember);
    }*/
    @GetMapping(path="/findSurgeryBySurgeryDate")
    public @ResponseBody
    List<Surgery> findSurgeryBySurgeryDate(@RequestParam Date surgeryDate,@RequestParam  UUID medicalInformationId){
        return fetchServiceSurgery.findSurgeryByCreationDate(surgeryDate,medicalInformationId);
    }
    @GetMapping(path="/findSurgeryByBodyMember")
    public @ResponseBody
    List<Surgery> findSurgeryByBodyMember(String bodyMember){
        return fetchServiceSurgery.findSurgeryByBodyMember(bodyMember);
    }
    @GetMapping(path="/findSurgeryByCreationDate")
    public @ResponseBody
    List<Surgery> findSurgeryByCreationDate(@RequestParam Date creationDate,@RequestParam UUID medicalInformationId){
        return fetchServiceSurgery.findSurgeryByCreationDate(creationDate,medicalInformationId);
    }






}
