package com.example.projectdeploy.Donate.Services;

import com.example.projectdeploy.Donate.DTO.UpdateStatusRequest;
import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.Donate.Model.DonateNotified;
import com.example.projectdeploy.Donate.Model.Status;
import com.example.projectdeploy.Donate.Repo.DonateNotifiedRepo;
import com.example.projectdeploy.Donate.Repo.DonateRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class DonateNotifiedUpdateStatus {
    @Autowired
    DonateNotifiedRepo donateNotifiedRepo;
    @Autowired
    DonateRepo donateRepo;

    Boolean AuthorizationForUpdate(UpdateStatusRequest updateStatusRequest){
        DonateNotified donateNotified=donateNotifiedRepo.findDonateById(updateStatusRequest.getDonateNotifiedId());
        if(updateStatusRequest.getDonator().toString().equals(donateNotified.getMedicalInformation().toString())&&
                (updateStatusRequest.getStatus().equals(Status.Agree)||updateStatusRequest.getStatus().equals(Status.Rejected))){
            if(updateStatusRequest.getStatus().equals(Status.Agree)&&(updateStatusRequest.getDateOfArrival()==null||
                    updateStatusRequest.getDateOfArrival().before(donateNotified.getDonate().getDonateDate()))){
                return false;
            }
            else return true;
        }
        else if (updateStatusRequest.getRequstor().toString().equals(donateNotified.getDonate().getRequestorMedicalInformation().toString())&&
                (updateStatusRequest.getStatus().equals(Status.Approval)||updateStatusRequest.getStatus().equals(Status.Come)
                ||updateStatusRequest.getStatus().equals(Status.DidNotCome))){
            long now = System.currentTimeMillis();
            Date DateNow = new Date(now);
            if(donateNotifiedRepo.findDonateNotifiedFoMedicalInformationByStatus(donateNotified.getId(),Status.Approval).size()>2
            ||((updateStatusRequest.getStatus().equals(Status.Come)
                    ||updateStatusRequest.getStatus().equals(Status.DidNotCome))&&DateNow.before(donateNotified.getDateOfArrival()))){
                return false;
            }
            return true;
        }
        else return false;
    }
    DonateNotified UpdateStatus(UpdateStatusRequest updateStatusRequest){
        if(!AuthorizationForUpdate(updateStatusRequest))return  null;
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
            donateRepo.save(donate);
        }
        donateNotifiedRepo.save(donateNotified);
        return donateNotified;
    }
}
