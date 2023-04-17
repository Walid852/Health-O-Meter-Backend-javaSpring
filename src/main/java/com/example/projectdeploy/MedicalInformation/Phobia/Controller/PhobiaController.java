package com.example.projectdeploy.MedicalInformation.Phobia.Controller;

import com.example.projectdeploy.MedicalInformation.Phobia.Request.PhobiaRequest;
import com.example.projectdeploy.MedicalInformation.Phobia.Request.PhobiaUpdate;
import com.example.projectdeploy.MedicalInformation.Phobia.Service.PhobiaService;
import com.example.projectdeploy.MedicalInformation.Phobia.model.Phobia;
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
    public @ResponseBody Phobia addPhobia(@RequestBody PhobiaRequest phobiaRequest){
        return phobiaService.addPhobia(phobiaRequest);
    }

    @PutMapping(value = "/updatePhobia")
    public @ResponseBody Phobia updatePhobia(@RequestBody PhobiaUpdate phobiaUpdate){
        return phobiaService.updatePhobia(phobiaUpdate);
    }

    @DeleteMapping(value = "/deletePhobia")
    public String deletePhobia(@RequestParam UUID id){
        return phobiaService.deletePhobia(id);
    }
    @GetMapping(value = "/getAllPhobia")
    public @ResponseBody
     List<Phobia> getAllPhobia()
    {
        return phobiaService.getAllPhobia();
    }
    @GetMapping(value = "/getPhobiaByMedicalInformationId")
    public @ResponseBody
    List<Phobia> getPhobiaByMedicalInformationId(@RequestParam UUID medicalInformationId){
        return phobiaService.getPhobiaByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getPhobiaDeletedByMedicalInformationId")
    public @ResponseBody
    List<Phobia> getPhobiaDeletedByMedicalInformationId(@RequestParam UUID medicalInformationId){
        return phobiaService.getPhobiaDeletedByMedicalInformationId(medicalInformationId);
    }
    @GetMapping(value = "/getPhobiaById")
    public @ResponseBody
    Phobia getPhobiaById(@RequestParam  UUID id){
        return phobiaService.getPhobiaById(id);
    }
    @GetMapping(value = "/getPhobiaByName")
    public @ResponseBody
    List<Phobia> getPhobiaByName(@RequestParam  String name)
    {
        return phobiaService.getPhobiaByName(name);
    }
    @GetMapping(value = "/getPhobiaByDate")
    public @ResponseBody
    List<Phobia> getPhobiaByDate(@RequestParam Date creationDate){
        return phobiaService.getPhobiaByDate(creationDate);
    }

}
