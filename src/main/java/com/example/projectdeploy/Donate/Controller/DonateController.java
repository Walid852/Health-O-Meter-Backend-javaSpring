package com.example.projectdeploy.Donate.Controller;

import com.example.projectdeploy.Donate.DTO.DonateRequest;
import com.example.projectdeploy.Donate.DTO.Resultt;
import com.example.projectdeploy.Donate.DTO.UpdateStatusRequest;
import com.example.projectdeploy.Donate.Model.Candidate;
import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.Donate.Model.DonateNotified;
import com.example.projectdeploy.Donate.Model.DonateResponse;
import com.example.projectdeploy.Donate.Services.CreateDonate;
import com.example.projectdeploy.Donate.Services.DonateNotifiedUpdateStatus;
import com.example.projectdeploy.Donate.Services.FetchServices;
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
    @Autowired
    FetchServices fetchServices;
    @PostMapping(path="/AddDonate")
    public @ResponseBody
    Response<DonateResponse> AddDonate(@RequestBody DonateRequest donateRequest){

        return createDonate.AddDonate(donateRequest);
    }
    @PostMapping(path="/ExpandingNotificationTransmission")
    public @ResponseBody
    Response<Donate> ExpandingNotificationTransmission(@RequestParam UUID donateId){

        return createDonate.ExpandingNotificationTransmission(donateId);
    }
    @PutMapping(path="/UpdateStatus")
    public @ResponseBody
    Response<Candidate> UpdateStatus(@RequestBody UpdateStatusRequest updateStatusRequest){
        return donateNotifiedUpdateStatus.UpdateStatus(updateStatusRequest);
    }
    @GetMapping(path="/GetCandidatesForRequestor")
    public @ResponseBody
    Response<Candidate> GetCandidatesForRequestor(@RequestParam UUID donatedId){
        return fetchServices.GetCandidatesForRequestor(donatedId);
    }
    @GetMapping(path="/GetMyDonation")
    public @ResponseBody
    Response<DonateResponse> GetMyDonation(@RequestParam UUID medicalInformationId){
        return fetchServices.GetMyDonation(medicalInformationId);
    }
    @GetMapping(path="/GetRequestsForYou")
    public @ResponseBody
    Response<DonateNotified> GetRequestsForYou(@RequestParam UUID medicalInformationId){
        return fetchServices.GetRequestsForYou(medicalInformationId);
    }


}
