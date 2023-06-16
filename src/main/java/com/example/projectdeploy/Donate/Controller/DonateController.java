package com.example.projectdeploy.Donate.Controller;

import com.example.projectdeploy.Donate.DTO.DonateRequest;
import com.example.projectdeploy.Donate.DTO.Resultt;
import com.example.projectdeploy.Donate.DTO.UpdateStatusRequest;
import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.Donate.Model.DonateNotified;
import com.example.projectdeploy.Donate.Services.CreateDonate;
import com.example.projectdeploy.Donate.Services.DonateNotifiedUpdateStatus;
import com.example.projectdeploy.Shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/Donate")
public class DonateController {
    @Autowired
    CreateDonate createDonate;
    @Autowired
    DonateNotifiedUpdateStatus donateNotifiedUpdateStatus;
    @PostMapping(path="/AddDonate")
    public @ResponseBody
    Response<Donate> AddDonate(@RequestBody DonateRequest donateRequest){

        return createDonate.AddDonate(donateRequest);
    }
    @PostMapping(path="/ExpandingNotificationTransmission")
    public @ResponseBody
    Response<Donate> ExpandingNotificationTransmission(@RequestParam UUID donateId){

        return createDonate.ExpandingNotificationTransmission(donateId);
    }
    @PutMapping(path="/UpdateStatus")
    public @ResponseBody
    Response<DonateNotified> UpdateStatus(UpdateStatusRequest updateStatusRequest){
        return donateNotifiedUpdateStatus.UpdateStatus(updateStatusRequest);
    }
}
