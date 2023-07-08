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
        System.out.println(updateStatusRequest.getStatus());
        System.out.println(updateStatusRequest.getDonateNotifiedId());
        System.out.println(updateStatusRequest.getRequstor());
        System.out.println(updateStatusRequest.getDonator());
        System.out.println(updateStatusRequest.getAm_pm());
        System.out.println(updateStatusRequest.getDateOfArrival());
        System.out.println(updateStatusRequest.getTime());
        System.out.println("WWWW");
        DonateNotified donateNotified=donateNotifiedRepo.findDonateById(updateStatusRequest.getDonateNotifiedId());
        System.out.println(donateNotified.getId());
        System.out.println(updateStatusRequest.getDonator());
        Candidate candidate=new Candidate();
        candidate.setDonateNotifiedId(donateNotified.getId());//
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
        System.out.println("WWW2");
        //.toString().equals(donateNotified.getMedicalInformation().toString())
        if(updateStatusRequest.getDonator()!=null&&
                (updateStatusRequest.getStatus().equals(Status.Agree)||updateStatusRequest.getStatus().equals(Status.Rejected))){
            System.out.println(1);
            if(updateStatusRequest.getStatus().equals(Status.Agree)&&
                    (
                            updateStatusRequest.getDateOfArrival()==null||
                            updateStatusRequest.getDateOfArrival().after(donateNotified.getDonate().getDonateDate())
                    )
              ){
                System.out.println(2);
                return new Response<>(false, StaticsText.MessageForTest("Can't do that make sure if agree add date before deadline ", ""),new ArrayList<>());
            }
            else return new Response<>(true, StaticsText.MessageForTest("change status", "successfully"), result);
        }
        //.toString().equals(donateNotified.getDonate().getRequestorMedicalInformation().toString())
        else if (updateStatusRequest.getRequstor()!=null&&
                (updateStatusRequest.getStatus().equals(Status.Approval)||updateStatusRequest.getStatus().equals(Status.Come)
                ||updateStatusRequest.getStatus().equals(Status.DidNotCome))){
            System.out.println(3);
            long now = System.currentTimeMillis();
            Date DateNow = new Date(now);
            if(donateNotifiedRepo.findDonateNotifiedFoMedicalInformationByStatus(donateNotified.getId(),Status.Approval).size()>1) {
                return new Response<>(false, StaticsText.MessageForTest("can't approval more than one", ""), new ArrayList<>());
            }
            if ((updateStatusRequest.getStatus().equals(Status.Come)
                    || updateStatusRequest.getStatus().equals(Status.DidNotCome)) && DateNow.after(donateNotified.getDateOfArrival())) {
                return new Response<>(false, StaticsText.MessageForTest("can't choose come or didn't come before deadline", ""), new ArrayList<>());
            }
            return new Response<>(true, StaticsText.MessageForTest("change status", "successfully"), result);
        }
        else return new Response<>(false, StaticsText.MessageForTest("change status", "Unsuccessfully"), new ArrayList<>());
    }
    public Response<Candidate> UpdateStatus(UpdateStatusRequest updateStatusRequest){
        try {
            Response<Candidate> donateNotifiedResponse=AuthorizationForUpdate(updateStatusRequest);
            if(!donateNotifiedResponse.status)return donateNotifiedResponse;
            System.out.println("donateNotified");
            DonateNotified donateNotified=donateNotifiedRepo.findDonateById(updateStatusRequest.getDonateNotifiedId());
            System.out.println(donateNotified.getId());
            donateNotified.setStatus(updateStatusRequest.getStatus());
            if(updateStatusRequest.getAm_pm()!=null)donateNotified.setAm_pm(updateStatusRequest.getAm_pm());//
            long now = System.currentTimeMillis();
            Date DateNow = new Date(now);
            donateNotified.setLastUpdateDate(DateNow);
            if(updateStatusRequest.getDateOfArrival()!=null)
                donateNotified.setDateOfArrival(updateStatusRequest.getDateOfArrival());
            if(updateStatusRequest.getStatus().equals(Status.Approval)){
                System.out.println("donate");
                Donate donate=donateRepo.findDonateById(donateNotified.getDonate().getId());
                System.out.println(donate.getId());
                donate.setDonatorMedicalInformation(donateNotified.getMedicalInformation());
                donate.setCurrent(LocationHierarchical.Terminate);
                donate.setIsDone(true);//
                donateRepo.save(donate);
            }
            donateNotifiedRepo.save(donateNotified);
            donateNotifiedResponse.data.get(0).setStatus(donateNotified.getStatus());
            return donateNotifiedResponse;
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
}
