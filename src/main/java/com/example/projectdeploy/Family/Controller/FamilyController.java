package com.example.projectdeploy.Family.Controller;

import com.example.projectdeploy.Family.Model.Family;
import com.example.projectdeploy.Family.Request.FamilyRequest;
import com.example.projectdeploy.Family.Service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class FamilyController {

    @Autowired
    FamilyService familyService;

    @PostMapping(value = "/addFamily")
    public @ResponseBody Family addFamilyMember(@RequestBody FamilyRequest familyRequest) {
        return familyService.addFamilyMember(familyRequest);
    }

    @GetMapping(value = "/getPendingList")
    public @ResponseBody List<Family> getPendingList(@RequestParam UUID userId){
        return familyService.getPendingList(userId);
    }

    @PutMapping(value = "/acceptFamilyRequest")
    public @ResponseBody Family acceptFamilyRequest(@RequestParam UUID familyId, @RequestParam UUID userId) {
        return familyService.acceptFamilyRequest(familyId,userId);
    }

    @DeleteMapping(value= "/deleteFamily")
    public @ResponseBody Family deleteFamily(@RequestParam UUID familyId, @RequestParam UUID userId){
        return familyService.deleteFamily(familyId,userId);
    }

    @DeleteMapping(value = "/rejectRequest")
    public @ResponseBody Family rejectRequest(@RequestParam UUID familyId, @RequestParam UUID userId){
        return familyService.rejectRequest(familyId,userId);
    }

    @GetMapping(value = "/getFamilyMembers")
    public @ResponseBody List<Family> getFamilyMembers(@RequestParam UUID userId){
        return familyService.getFamilyMembers(userId);
    }

    @PutMapping(value = "/updateFamily")
    public @ResponseBody Family updateFamily(@RequestBody FamilyRequest familyRequest){
        return familyService.updateFamily(familyRequest);
    }


    }
