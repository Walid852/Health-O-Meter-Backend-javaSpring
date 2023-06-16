package com.example.projectdeploy.Donate.Services;

import com.example.projectdeploy.Donate.Model.Candidate;
import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.Donate.Model.DonateNotified;
import com.example.projectdeploy.Donate.Model.Status;
import com.example.projectdeploy.Donate.Repo.DonateNotifiedRepo;
import com.example.projectdeploy.Donate.Repo.DonateRepo;
import com.example.projectdeploy.MedicalInformation.CrudServiceMedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Notification.Repo.NotificationRepo;
import com.example.projectdeploy.Notification.Services.NotificationServices;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FetchServices {
    @Autowired
    DonateRepo donateRepo;
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Autowired
    DonateNotifiedRepo donateNotifiedRepo;
    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    NotificationServices notificationServices;
    @Autowired
    CrudServiceMedicalInformation crudServiceMedicalInformation;
    public Response<Candidate> GetCandidatesForRequestor(UUID donatedId){
        try {
            List<Candidate> candidateList=new LinkedList<>();
            List<Status> statusList= Arrays.asList(Status.Pending, Status.Rejected, Status.Notified);
            List<DonateNotified> donateNotifiedList=donateNotifiedRepo.findDonateNotifiedByDonateIdForRequestor(donatedId,statusList);
            for (DonateNotified DN:
                    donateNotifiedList) {
                Candidate candidate=new Candidate();
                candidate.setMedicalInformationId(DN.getMedicalInformation().getId());
                candidate.setBloodType(DN.getMedicalInformation().getBloodType());
                candidate.setDateOfArrival(DN.getDateOfArrival());
                candidate.setLastUpdateDate(DN.getLastUpdateDate());
                candidate.setStatus(DN.getStatus());
                candidate.setName(DN.getMedicalInformation().getUser().getName());
                candidate.setNationalId(DN.getMedicalInformation().getUser().getNationalId());
                candidate.setPhoto(DN.getMedicalInformation().getUser().getPhoto());
                candidate.setPhoneNumber(DN.getMedicalInformation().getUser().getPhoneNumber());
                candidate.setUsername(DN.getMedicalInformation().getUser().getUserName());
                candidateList.add(candidate);
            }
            return new Response<>(true, StaticsText.MessageForTest("Candidates", "Retrieved"), candidateList);
        }catch (Exception e){
            return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<Donate> GetMyDonation(UUID medicalInformationId){
        try{
            List<Donate> donateList=donateRepo.findMyDonateByMedicalInformationId(medicalInformationId);
            return new Response<>(true, StaticsText.MessageForTest("GetMyDonation", "Retrieved"), donateList);
        }catch (Exception e){
            return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<DonateNotified> GetRequestsForYou(UUID medicalInformationId){
        try{
            List<Status> statusList= Arrays.asList(Status.Pending, Status.Rejected);
            List<DonateNotified> donateNotifiedList=
                    donateNotifiedRepo.findDonateNotifiedFoMedicalInformation(medicalInformationId,statusList);

            return new Response<>(true, StaticsText.MessageForTest("GetMyDonation", "Retrieved"), donateNotifiedList);
        }catch (Exception e){
            return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTestError());
        }
    }

}
