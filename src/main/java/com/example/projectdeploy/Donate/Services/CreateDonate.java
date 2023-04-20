package com.example.projectdeploy.Donate.Services;

import com.example.projectdeploy.Donate.DTO.DonateRequest;
import com.example.projectdeploy.Donate.DTO.Resultt;
import com.example.projectdeploy.Donate.Model.Donate;
import com.example.projectdeploy.Donate.Model.DonateNotified;
import com.example.projectdeploy.Donate.Model.LocationHierarchical;
import com.example.projectdeploy.Donate.Model.Status;
import com.example.projectdeploy.Donate.Repo.DonateNotifiedRepo;
import com.example.projectdeploy.Donate.Repo.DonateRepo;
import com.example.projectdeploy.Map.Model.Response;
import com.example.projectdeploy.Map.Model.Result;
import com.example.projectdeploy.Map.Model.UserLocation;
import com.example.projectdeploy.Map.Service.LocationService;
import com.example.projectdeploy.MedicalInformation.CrudServiceMedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Notification.Model.ConstantMessage;
import com.example.projectdeploy.Notification.Model.NotificationRequest;
import com.example.projectdeploy.Notification.Repo.NotificationRepo;
import com.example.projectdeploy.Notification.Services.NotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


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

    private static final Object API_KEY = "AIzaSyBNo0YT5YEAKoIP13yyGUkEIrnJKCNQ0-8";
    List<DonateNotified> AddMedicalInformationValidateToDonate(List<MedicalInformation> medicalInformationList, Donate donate){
        List<DonateNotified> donateNotifiedList=new LinkedList<>();
        System.out.println("size:   "+medicalInformationList.size());
        for (MedicalInformation MI:medicalInformationList) {
            long now = System.currentTimeMillis();
            Date DateNow = new Date(now);
            DonateNotified donateNotified=new DonateNotified(MI,donate, Status.Pending, (java.sql.Date) DateNow);
            donateNotifiedList.add(donateNotified);
            donateNotifiedRepo.save(donateNotified);
        }
        return donateNotifiedList;
    }
    void SendNotification(DonateNotified donateNotified){
                donateNotified.setStatus(Status.Notified);
                long now = System.currentTimeMillis();
                Date DateNow = new Date(now);
                NotificationRequest notificationRequest=new NotificationRequest(donateNotified.getMedicalInformation().getUser().getId()
                        ,ConstantMessage.titleDonation
                        ,donateNotified.getMedicalInformation().getUser().getName()+ConstantMessage.messageDonation
                        ,ConstantMessage.urlDonation,ConstantMessage.photoDonation
                        , (java.sql.Date) DateNow);
                notificationServices.AddNotification(notificationRequest);
                donateNotifiedRepo.save(donateNotified);
    }
    String ExpandingNotificationTransmission(Donate donate){
        List<DonateNotified> donateNotifiedList=donateNotifiedRepo.findDonateNotifiedByDonateId(donate.getId());
      if(donate.getDonatorMedicalInformation()!=null){
          return ConstantMessage.UnSuccessExpand;
      }
      else if(donate.getCurrent()== LocationHierarchical.street){
          for (DonateNotified DN:donateNotifiedList) {
              if(DN.getStatus()==Status.Pending&&
                      donate.getLocation().getStreet().equals(DN.getMedicalInformation().getUser().getLocation().getStreet())){
                  SendNotification(DN);
              }
          }
          donate.setCurrent(LocationHierarchical.postal_code);
          return ConstantMessage.SuccessExpand;
      }
      else if(donate.getCurrent()== LocationHierarchical.postal_code){
          for (DonateNotified DN:donateNotifiedList) {
              if(DN.getStatus()==Status.Pending&&
                      donate.getLocation().getPostal_code().equals(DN.getMedicalInformation().getUser().getLocation().getPostal_code())){
                  SendNotification(DN);
              }
          }
          donate.setCurrent(LocationHierarchical.city);
          return ConstantMessage.SuccessExpand;
        }
      else if(donate.getCurrent()== LocationHierarchical.city){
          for (DonateNotified DN:donateNotifiedList) {
              if(DN.getStatus()==Status.Pending&&
                      donate.getLocation().getCity().equals(DN.getMedicalInformation().getUser().getLocation().getCity())){
                  SendNotification(DN);
              }
          }
          donate.setCurrent(LocationHierarchical.government);
          return ConstantMessage.SuccessExpand;
        }
      else if(donate.getCurrent()== LocationHierarchical.government){
          for (DonateNotified DN:donateNotifiedList) {
              if(DN.getStatus()==Status.Pending&&
                      donate.getLocation().getGovernment().equals(DN.getMedicalInformation().getUser().getLocation().getGovernment())){
                  SendNotification(DN);
              }
          }
          donate.setCurrent(LocationHierarchical.Terminate);
          return ConstantMessage.SuccessExpand;
      }
      else{
          return ConstantMessage.UnSuccessExpand;
      }
    }
   public Resultt CreateDonate(DonateRequest donateRequest){
        Resultt resultt=new Resultt();
       UriComponents uri = UriComponentsBuilder.newInstance()
               .scheme("https")
               .host("maps.googleapis.com")
               .path("/maps/api/geocode/json")
               .queryParam("key", API_KEY)
               .queryParam("latlng",donateRequest.getLating())
               .build();
       System.out.println(uri.toUriString());
       ResponseEntity<Response> response = new RestTemplate().getForEntity(uri.toUriString(), Response.class);
       Result[] results= Objects.requireNonNull(response.getBody()).getResults();
       System.out.println(results[0]);
       System.out.println(Objects.requireNonNull(response.getBody()).getResults().length);
       UserLocation location= locationService.AddLocation(results[0]);

        MedicalInformation medicalInformation= medicalInformationRepo.findMedicalInformationById(
                donateRequest.getRequesterMedicalInformationId()
        );
        Donate donate=new Donate(medicalInformation,donateRequest.getDonateDate(),donateRequest.getBloodType(),location);
       donateRepo.save(donate);
       resultt.setDonate(donate);
       List<MedicalInformation> medicalInformationList=crudServiceMedicalInformation.ValidateToDonate(donate.getBloodType(),
               donate.getRequestorMedicalInformation().getUser().getLocation().getGovernment());
       resultt.setMedicalInformationList(medicalInformationList);
       System.out.println("Size2:  "+medicalInformationList.size());
       List<DonateNotified> donateNotifiedList=AddMedicalInformationValidateToDonate(medicalInformationList,donate);
       resultt.setDonateNotified(donateNotifiedList);
       ExpandingNotificationTransmission(donate);
       return resultt;
    }


}
