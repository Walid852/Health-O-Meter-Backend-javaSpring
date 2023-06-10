package com.example.projectdeploy.MedicalInformation.Phobia.Controller;

import com.example.projectdeploy.MedicalInformation.Phobia.Request.PhobiaRequest;
import com.example.projectdeploy.MedicalInformation.Phobia.Request.PhobiaUpdate;
import com.example.projectdeploy.MedicalInformation.Phobia.Service.PhobiaService;
import com.example.projectdeploy.MedicalInformation.Phobia.model.Phobia;
import com.example.projectdeploy.MedicalInformation.Surgery.Model.Surgery;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/Phobia")
public class PhobiaController {

    @Autowired
    PhobiaService phobiaService;

    @PostMapping(value = "/addPhobia")
    public @ResponseBody
    Response<Phobia>  addPhobia(@RequestBody PhobiaRequest phobiaRequest){
        return phobiaService.addPhobia(phobiaRequest);
    }

    @PutMapping(value = "/updatePhobia")
    public @ResponseBody Response<Phobia> updatePhobia(@RequestBody PhobiaUpdate phobiaUpdate){
        return phobiaService.updatePhobia(phobiaUpdate);
    }

    @DeleteMapping(value = "/deletePhobia")
    public Response<Phobia> deletePhobia(@RequestParam UUID id){
        return phobiaService.deletePhobia(id);
    }
    @GetMapping(value = "/getAllPhobia")
    public @ResponseBody
    Response<Phobia> getAllPhobia()
    {
        return phobiaService.getAllPhobia();
    }
    @GetMapping(value = "/getPhobiaByMedicalInformationId")
    public @ResponseBody
    Response<Phobia> getPhobiaByMedicalInformationId(@RequestParam UUID medicalInformationId){
        return phobiaService.getPhobiaByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getPhobiaDeletedByMedicalInformationId")
    public @ResponseBody
    Response<Phobia> getPhobiaDeletedByMedicalInformationId(@RequestParam UUID medicalInformationId){
        return phobiaService.getPhobiaDeletedByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getPhobiaById")
    public @ResponseBody
    Response<Phobia> getPhobiaById(@RequestParam  UUID id){
        return phobiaService.getPhobiaById(id);
    }
    @GetMapping(value = "/getPhobiaByName")
    public @ResponseBody
    Response<Phobia> getPhobiaByName(@RequestParam  String name)
    {
        return phobiaService.getPhobiaByName(name);
    }
    @GetMapping(value = "/getPhobiaByDate")
    public @ResponseBody
    Response<Phobia> getPhobiaByDate(@RequestParam Date creationDate){
        return phobiaService.getPhobiaByDate(creationDate);
    }

}
