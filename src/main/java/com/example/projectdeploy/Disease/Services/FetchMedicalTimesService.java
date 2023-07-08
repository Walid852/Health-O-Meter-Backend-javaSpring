package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.Models.MedicineTime;
import com.example.projectdeploy.Disease.Repos.MedicineTimeRepo;
import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.Notification.Model.NotificationRequest;
import com.example.projectdeploy.Notification.Model.TypeUrl;
import com.example.projectdeploy.Notification.Services.NotificationServices;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FetchMedicalTimesService {
    @Autowired
    MedicineTimeRepo medicineTimeRepo;

    @Autowired
    NotificationServices notificationServices;


    public Response<MedicineTime>  findMedicineTimeById(UUID id){
        try {
            List<MedicineTime> result=new ArrayList<>();
            MedicineTime medicineTime=medicineTimeRepo.findMedicineTimeById(id);
            if(medicineTime!=null) {
                result.add(medicineTime);
            }
            else return new Response<>(false, StaticsText.MessageForTest("medicineTime", "not Found"));

            return new Response<>(true, StaticsText.MessageForTest("MedicineTime", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public Response<MedicineTime>  findMedicineTimeByMedicineId(UUID id){

        try {
            List<MedicineTime> result=medicineTimeRepo.findMedicineTimeByMedicineId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("MedicineTime", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("MedicineTime", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public Response<MedicineTime>  findMedicineTimeByDiseaseId(UUID id){
        try {
            List<MedicineTime> result=medicineTimeRepo.findMedicineTimeByDiseaseId(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("MedicineTime", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("MedicineTime", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public Response<MedicineTime>  findMedicineTimeByMedicalInformationIdAndNotified(UUID id){
        try {
            List<MedicineTime> result=medicineTimeRepo.findMedicineTimeByMedicalInformationIdAndNotified(id);
            if(result.size()==0)return new Response<>(false, StaticsText.MessageForTest("MedicineTime", "not Found"));
            return new Response<>(true, StaticsText.MessageForTest("MedicineTime", "Retrieved"),result);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    @Transactional
    //@Scheduled(fixedRate = 120000)
    public void sendMedicineNotification(){
        List<MedicineTime> medicineTimes=medicineTimeRepo.findMedicineTimeBeforeDate(new Date(System.currentTimeMillis()));
        for(MedicineTime medicineTime:medicineTimes){
                User toUser=medicineTime.getMedicine().getDisease().getMedicalInformation().getUser();
                NotificationRequest notificationRequest=new NotificationRequest(toUser.getId(),toUser.getId(),
                        "Medicine reminder",String.format("It is time to take %s",medicineTime.getMedicine().getName()),medicineTime.getId(), TypeUrl.Non,"",
                        java.sql.Date.valueOf(LocalDate.now()));
                notificationServices.AddNotification(notificationRequest);
            System.out.println(medicineTime.getMedicine().getName());
            medicineTime.setMedicine(null);

        }
        System.out.println("i am here");
        medicineTimeRepo.deleteAll(medicineTimes);

    }

}
