package com.example.projectdeploy.Donate.Services;

import com.example.projectdeploy.Donate.DTO.UpdateStatusRequest;
import com.example.projectdeploy.Donate.Model.*;
import com.example.projectdeploy.Donate.Repo.DonateNotifiedRepo;
import com.example.projectdeploy.Donate.Repo.DonateRepo;
import com.example.projectdeploy.Notification.Model.ConstantMessage;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Service
public class DonateNotifiedUpdateStatus {
    @Autowired
    DonateNotifiedRepo donateNotifiedRepo;
    @Autowired
    DonateRepo donateRepo;

    Response<Candidate> AuthorizationForUpdate(UpdateStatusRequest updateStatusRequest){
        DonateNotified donateNotified=donateNotifiedRepo.findDonateById(updateStatusRequest.getDonateNotifiedId());
        Candidate candidate=new Candidate();
        candidate.setMedicalInformationId(donateNotified.getMedicalInformation().getId());
        candidate.setBloodType(donateNotified.getMedicalInformation().getBloodType());
        candidate.setDateOfArrival(donateNotified.getDateOfArrival());
        candidate.setLastUpdateDate(donateNotified.getLastUpdateDate());
        candidate.setStatus(donateNotified.getStatus());
        candidate.setName(donateNotified.getMedicalInformation().getUser().getName());
        candidate.setNationalId(donateNotified.getMedicalInformation().getUser().getNationalId());
        candidate.setPhoto(donateNotified.getMedicalInformation().getUser().getPhoto());
        candidate.setPhoneNumber(donateNotified.getMedicalInformation().getUser().getPhoneNumber());
        candidate.setUsername(donateNotified.getMedicalInformation().getUser().getUserName());
        List<Candidate> result = new ArrayList<>();
        result.add(candidate);
        if(updateStatusRequest.getDonator().toString().equals(donateNotified.getMedicalInformation().toString())&&
                (updateStatusRequest.getStatus().equals(Status.Agree)||updateStatusRequest.getStatus().equals(Status.Rejected))){
            if(     updateStatusRequest.getStatus().equals(Status.Agree)&&
                    (
                            updateStatusRequest.getDateOfArrival()==null||
                            updateStatusRequest.getDateOfArrival().after(donateNotified.getDonate().getDonateDate())
                    )
              ){
                return new Response<>(false, StaticsText.MessageForTest("Can't do that make sure if agree add date before deadline ", ""),result);
            }
            else return new Response<>(true, StaticsText.MessageForTest("change status", "successfully"), result);
        }
        else if (updateStatusRequest.getRequstor().toString().equals(donateNotified.getDonate().getRequestorMedicalInformation().toString())&&
                (updateStatusRequest.getStatus().equals(Status.Approval)||updateStatusRequest.getStatus().equals(Status.Come)
                ||updateStatusRequest.getStatus().equals(Status.DidNotCome))){
            long now = System.currentTimeMillis();
            Date DateNow = new Date(now);
            if(donateNotifiedRepo.findDonateNotifiedFoMedicalInformationByStatus(donateNotified.getId(),Status.Approval).size()>1) {
                return new Response<>(false, StaticsText.MessageForTest("can't approval more than one", ""), result);
            }
            if ((updateStatusRequest.getStatus().equals(Status.Come)
                    || updateStatusRequest.getStatus().equals(Status.DidNotCome)) && DateNow.after(donateNotified.getDateOfArrival())) {
                return new Response<>(false, StaticsText.MessageForTest("can't choose come or didn't come before deadline", ""), result);
            }
            return new Response<>(true, StaticsText.MessageForTest("change status", "successfully"), result);
        }
        else return new Response<>(true, StaticsText.MessageForTest("change status", "Unsuccessfully"), result);
    }
    public Response<Candidate> UpdateStatus(UpdateStatusRequest updateStatusRequest){
        try {
            Response<Candidate> donateNotifiedResponse=AuthorizationForUpdate(updateStatusRequest);
            if(!donateNotifiedResponse.status)return donateNotifiedResponse;
            DonateNotified donateNotified=donateNotifiedRepo.findDonateById(updateStatusRequest.getDonateNotifiedId());
            donateNotified.setStatus(updateStatusRequest.getStatus());
            long now = System.currentTimeMillis();
            Date DateNow = new Date(now);
            donateNotified.setLastUpdateDate(DateNow);
            if(updateStatusRequest.getDateOfArrival()!=null)
                donateNotified.setDateOfArrival(updateStatusRequest.getDateOfArrival());
            if(updateStatusRequest.getStatus().equals(Status.Approval)){
                Donate donate=donateRepo.findDonateById(donateNotified.getDonate().getId());
                donate.setDonatorMedicalInformation(donateNotified.getMedicalInformation());
                donate.setCurrent(LocationHierarchical.Terminate);
                donateRepo.save(donate);
            }
            donateNotifiedRepo.save(donateNotified);
            return donateNotifiedResponse;
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
}
