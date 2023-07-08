package com.example.projectdeploy.Donate.Services;

import com.example.projectdeploy.Donate.DTO.DonateRequest;
import com.example.projectdeploy.Donate.Model.*;

import com.example.projectdeploy.Donate.Repo.DonateNotifiedRepo;
import com.example.projectdeploy.Donate.Repo.DonateRepo;
import com.example.projectdeploy.Map.Model.UserLocation;
import com.example.projectdeploy.Map.Service.LocationService;
import com.example.projectdeploy.MedicalInformation.CrudServiceMedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Notification.Model.ConstantMessage;
import com.example.projectdeploy.Notification.Model.NotificationRequest;
import com.example.projectdeploy.Notification.Model.TypeUrl;
import com.example.projectdeploy.Notification.Repo.NotificationRepo;
import com.example.projectdeploy.Notification.Services.NotificationServices;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Service
public class CreateDonate {
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
    @Autowired
    LocationService locationService;

    private static final Object API_KEY = "AIzaSyDQE_OqbesINOGfLOhflK5uGUbVFJXe7L0";
    List<DonateNotified> AddMedicalInformationValidateToDonate(List<MedicalInformation> medicalInformationList, Donate donate){
        List<DonateNotified> donateNotifiedList=new LinkedList<>();
        System.out.println("size:   "+medicalInformationList.size());
        for (MedicalInformation MI:medicalInformationList) {
            long now = System.currentTimeMillis();
            Date DateNow = new Date(now);
            DonateNotified donateNotified=new DonateNotified(MI,donate, Status.Pending, DateNow);
            donateNotifiedList.add(donateNotified);
            donateNotifiedRepo.save(donateNotified);
        }
        return donateNotifiedList;
    }
    void SendNotification(DonateNotified donateNotified){
                donateNotified.setStatus(Status.Notified);
                long now = System.currentTimeMillis();
                Date DateNow = new Date(now);
                NotificationRequest notificationRequest=new NotificationRequest(
                        donateNotified.getDonate().getRequestorMedicalInformation().getUser().getId()
                        ,donateNotified.getMedicalInformation().getUser().getId()
                        ,ConstantMessage.titleDonation
                        ,donateNotified.getMedicalInformation().getUser().getName()+ConstantMessage.messageDonation
                        ,donateNotified.getId()
                        , TypeUrl.DonateRequest
                        ,ConstantMessage.photoDonation
                        ,DateNow);
                notificationServices.AddNotification(notificationRequest);
                donateNotifiedRepo.save(donateNotified);
    }

    public com.example.projectdeploy.Shared.Response<Donate> ExpandingNotificationTransmission(UUID donateId){
        Donate donate=donateRepo.findDonateById(donateId);
        List<Donate> result = new ArrayList<>();
        result.add(donate);
        List<DonateNotified> donateNotifiedList=donateNotifiedRepo.findDonateNotifiedByDonateId(donate.getId());
      if(donate.getDonatorMedicalInformation()!=null){
          return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTest(ConstantMessage.UnSuccessExpand, ""),result);
      }
      else if(donate.getCurrent()== LocationHierarchical.street){
          for (DonateNotified DN:donateNotifiedList) {
              if(DN.getStatus()==Status.Pending&&
                      donate.getLocation().getStreet().equals(DN.getMedicalInformation().getUser().getLocation().getStreet())){
                  SendNotification(DN);
              }
          }
          donate.setCurrent(LocationHierarchical.postal_code);
          donateRepo.save(donate);
          return new com.example.projectdeploy.Shared.Response<>(true, StaticsText.MessageForTest(ConstantMessage.SuccessExpand, ""),result);
      }
      else if(donate.getCurrent()== LocationHierarchical.postal_code){
          for (DonateNotified DN:donateNotifiedList) {
              if(DN.getStatus()==Status.Pending&&
                      donate.getLocation().getPostal_code().equals(DN.getMedicalInformation().getUser().getLocation().getPostal_code())){
                  SendNotification(DN);
              }
          }
          donate.setCurrent(LocationHierarchical.city);
          return new com.example.projectdeploy.Shared.Response<>(true, StaticsText.MessageForTest(ConstantMessage.SuccessExpand, ""),result);
        }
      else if(donate.getCurrent()== LocationHierarchical.city){
          for (DonateNotified DN:donateNotifiedList) {
              if(DN.getStatus()==Status.Pending&&
                      donate.getLocation().getCity().equals(DN.getMedicalInformation().getUser().getLocation().getCity())){
                  SendNotification(DN);
              }
          }
          donate.setCurrent(LocationHierarchical.government);
          return new com.example.projectdeploy.Shared.Response<>(true, StaticsText.MessageForTest(ConstantMessage.SuccessExpand, ""),result);
        }
      else if(donate.getCurrent()== LocationHierarchical.government){
          for (DonateNotified DN:donateNotifiedList) {
              if(DN.getStatus()==Status.Pending&&
                      donate.getLocation().getGovernment().equals(DN.getMedicalInformation().getUser().getLocation().getGovernment())){
                  SendNotification(DN);
              }
          }
          donate.setCurrent(LocationHierarchical.Terminate);
          return new com.example.projectdeploy.Shared.Response<>(true, StaticsText.MessageForTest(ConstantMessage.SuccessExpand, ""),result);
      }
      else{
          return new com.example.projectdeploy.Shared.Response<>(true, StaticsText.MessageForTest(ConstantMessage.UnSuccessExpand, ""),result);
      }
    }
   public com.example.projectdeploy.Shared.Response<DonateResponse> AddDonate(DonateRequest donateRequest){
        try {
            UserLocation location=locationService.SaveLocation(donateRequest.getLating());
            MedicalInformation medicalInformation=null;
            if(medicalInformationRepo.findById(donateRequest.getRequesterMedicalInformationId()).isPresent()){
                medicalInformation= medicalInformationRepo.findMedicalInformationById(
                        donateRequest.getRequesterMedicalInformationId());
                if(medicalInformation.getUser().getLocation()==null){
                    return new com.example.projectdeploy.Shared.Response<>(false,
                            StaticsText.MessageForTest("user", "has no location"));
                }

            }
            else {
                return new com.example.projectdeploy.Shared.Response<>(false,
                        StaticsText.MessageForTest("medicalInformation", "not Found"));
            }
            System.out.println("medicalInformation: "+medicalInformation);
            Donate donate=new Donate(medicalInformation,donateRequest.getDonateDate(),donateRequest.getBloodType(),location);
            System.out.println("donate: "+donate);
            System.out.println();
            List<MedicalInformation> medicalInformationList=new LinkedList<>();
            if(donate.getRequestorMedicalInformation().getUser().getLocation().getGovernment()!=null){
                medicalInformationList=crudServiceMedicalInformation.ValidateToDonate(donate.getBloodType(),
                        donate.getRequestorMedicalInformation().getUser().getLocation().getGovernment());
            }
            System.out.println(medicalInformationList);
            Donate donate1=donateRepo.save(donate);
            System.out.println("Size2:  "+medicalInformationList.size());
            AddMedicalInformationValidateToDonate(medicalInformationList,donate);
            ExpandingNotificationTransmission(donate.getId());
            System.out.println("LLLLLL");
            List<DonateResponse> result = new ArrayList<>();
            DonateResponse donateResponse=MapToDonateResponse(donate1);
            result.add(donateResponse);
            return new com.example.projectdeploy.Shared.Response<>(true, StaticsText.MessageForTest("Donate ", "Created"), result);
        }catch (Exception e){
            System.out.println(e);
            return new com.example.projectdeploy.Shared.Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public DonateResponse MapToDonateResponse(Donate donate){
        UUID donatorMedicalInformation=null;
        if (donate.getDonatorMedicalInformation() != null) {
            donatorMedicalInformation=donate.getDonatorMedicalInformation().getId();
        }
        return new DonateResponse(donate.getId(),donate.getDonateDate(),donate.getIsDone(),donate.getBloodType(),donate.getLocation().getLat(),
                    donate.getLocation().getLng(),donate.getCurrent(),donate.getRequestorMedicalInformation().getId(),
                     donatorMedicalInformation);

    }


    @Scheduled(fixedRate = 300000)
    public void ExpandingNotificationTransmission(){
        List<Donate> donates=donateRepo.findAll();
        for(Donate donate:donates) {
            List<DonateNotified> donateNotifiedList = donateNotifiedRepo.findDonateNotifiedByDonateId(donate.getId());
            if (donate.getDonatorMedicalInformation() != null) {
            } else if (donate.getCurrent() == LocationHierarchical.street) {
                for (DonateNotified DN : donateNotifiedList) {
                    if (DN.getStatus() == Status.Pending &&
                            donate.getLocation().getStreet().equals(DN.getMedicalInformation().getUser().getLocation().getStreet())) {
                        SendNotification(DN);
                    }
                }
                donate.setCurrent(LocationHierarchical.postal_code);
            } else if (donate.getCurrent() == LocationHierarchical.postal_code) {
                for (DonateNotified DN : donateNotifiedList) {
                    if (DN.getStatus() == Status.Pending &&
                            donate.getLocation().getPostal_code().equals(DN.getMedicalInformation().getUser().getLocation().getPostal_code())) {
                        SendNotification(DN);
                    }
                }
                donate.setCurrent(LocationHierarchical.city);
            } else if (donate.getCurrent() == LocationHierarchical.city) {
                for (DonateNotified DN : donateNotifiedList) {
                    if (DN.getStatus() == Status.Pending &&
                            donate.getLocation().getCity().equals(DN.getMedicalInformation().getUser().getLocation().getCity())) {
                        SendNotification(DN);
                    }
                }
                donate.setCurrent(LocationHierarchical.government);
            } else if (donate.getCurrent() == LocationHierarchical.government) {
                for (DonateNotified DN : donateNotifiedList) {
                    if (DN.getStatus() == Status.Pending &&
                            donate.getLocation().getGovernment().equals(DN.getMedicalInformation().getUser().getLocation().getGovernment())) {
                        SendNotification(DN);
                    }
                }
                donate.setCurrent(LocationHierarchical.Terminate);
            }
            donateRepo.save(donate);
        }
    }

}
