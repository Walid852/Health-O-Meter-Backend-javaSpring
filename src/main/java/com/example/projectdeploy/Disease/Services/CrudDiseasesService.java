package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.DTO.DiseaseDto;
import com.example.projectdeploy.Disease.DTO.MedicineDto;
import com.example.projectdeploy.Disease.Models.Disease;
import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Models.MedicineTime;
import com.example.projectdeploy.Disease.Repos.DiseaseRepo;
import com.example.projectdeploy.Disease.Repos.MedicineRepo;
import com.example.projectdeploy.Disease.Repos.MedicineTimeRepo;
import com.example.projectdeploy.MedicalInformation.Allergic.Model.Allergy;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CrudDiseasesService {
    @Autowired
    MedicalInformationRepo medicalInformationRepo;
    @Autowired
    DiseaseRepo diseaseRepo;
    @Autowired
    MedicineRepo medicineRepo;
    @Autowired
    MedicineTimeRepo medicineTimeRepo;
    /*long now = System.currentTimeMillis();
            Date sqlDate = new Date(now);*/
    @Transactional
    public Response<Disease> AddDisease(DiseaseDto creationDisease){
        try {
            MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(creationDisease.getMedicalInformationId());
            if (medicalInformation==null) {
                return new Response<>(false, StaticsText.MessageForTest("error", "not have a name"));
            }
            long milliseconds1 = creationDisease.getStartDate().getTime();
            long milliseconds2 = creationDisease.getEndDate().getTime();
            long millisecondsDiff = milliseconds2 - milliseconds1;
            long daysDiff = millisecondsDiff / (1000 * 60 * 60 * 24);
            Disease disease=new Disease(medicalInformation,creationDisease.getName()
                    ,creationDisease.getDescription(),creationDisease.getStartDate()
                    ,creationDisease.getEndDate(), creationDisease.getIsCured(),daysDiff);
            diseaseRepo.save(disease);
            List<Disease> result = new ArrayList<>();
            result.add(disease);
            return new Response<>(true, StaticsText.MessageForTest("Disease", "Created"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<Disease> UpdateDisease(DiseaseDto creationDisease){
        try {
            Disease disease=diseaseRepo.findDiseaseById(creationDisease.getDiseaseId());
            if(disease==null){
                return new Response<>(false, StaticsText.MessageForTest("error", "not have this Disease"));
            }
            if(creationDisease.getName()!=null)disease.setName(creationDisease.getName());
            if(creationDisease.getDescription()!=null)disease.setDescription(creationDisease.getDescription());
            if(creationDisease.getStartDate()!=null)disease.setStartDate(creationDisease.getStartDate());
            if(creationDisease.getEndDate()!=null)disease.setEndDate(creationDisease.getEndDate());
            if(creationDisease.getIsCured()!=null)disease.setIsCured(creationDisease.getIsCured());
            diseaseRepo.save(disease);
            List<Disease> result = new ArrayList<>();
            result.add(disease);
            return new Response<>(true, StaticsText.MessageForTest("Disease", "Updated"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public Response<Disease> DeleteDisease(UUID diseaseId){
        try {
            List<Medicine> medicines=medicineRepo.findMedicineForDisease(diseaseId);
            for (Medicine M:medicines) {
                List<MedicineTime> medicineTimes = medicineTimeRepo.findMedicineTimeByMedicineId(M.getId());
                medicineTimeRepo.deleteAll(medicineTimes);
                Medicine medicine=medicineRepo.findMedicineById(M.getId());
                medicine.setIsDeleted(true);
                medicineRepo.save(medicine);
            }
            Disease disease=diseaseRepo.findDiseaseById(diseaseId);
            disease.setIsDeleted(true);
            diseaseRepo.save(disease);
            List<Disease> result = new ArrayList<>();
            result.add(disease);
            return new Response<>(true, StaticsText.MessageForTest("Disease and its medication", "Deleted"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }
    public Response<Medicine> AddMedicine(MedicineDto M){
        try {
            Disease disease=diseaseRepo.findDiseaseById(M.getDiseaseId());
            Medicine medicine=new Medicine(M.getName(),M.getIsNotified(),M.getStartDate(),M.getNumberOfDays(),
                    M.getNumberOfTakesPerDay(),disease);
            List<Date> intervals =Interval.CalculatedIntervals(medicine);
            System.out.println(intervals.size());
            medicineRepo.save(medicine);
            System.out.println("lllll");
            for (Date D:intervals) {
                MedicineTime medicineTime=new MedicineTime(medicine,D);
                medicineTimeRepo.save(medicineTime);
            }
            List<Medicine> result = new ArrayList<>();
            result.add(medicine);
            return new Response<>(true, StaticsText.MessageForTest("medicine", "Added"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<Medicine> UpdateMedicine(MedicineDto M){
        try {
            int f=0;
            Medicine medicine=medicineRepo.findMedicineById(M.getMedicineId());
            if(M.getIsNotified()!=null)medicine.setIsNotified(M.getIsNotified());
            if(M.getName()!=null)medicine.setName(M.getName());
            if(M.getNumberOfDays()==-1&&M.getNumberOfTakesPerDay()==-1)f=1;
            if(f==0){
                if (M.getNumberOfDays() != -1) medicine.setNumberOfDays(M.getNumberOfDays());
                if (M.getNumberOfTakesPerDay() != -1) medicine.setNumberOfTakesPerDay(M.getNumberOfTakesPerDay());
                medicineRepo.save(medicine);
                List<MedicineTime> medicineTimes = medicineTimeRepo.findMedicineTimeByMedicineId(medicine.getId());
                medicineTimeRepo.deleteAll(medicineTimes);
                List<Date> intervals = Interval.CalculatedIntervals(medicine);
                for (Date D : intervals) {
                    MedicineTime medicineTime = new MedicineTime(medicine, D);
                    medicineTimeRepo.save(medicineTime);
                }
            }
            medicineRepo.save(medicine);
            List<Medicine> result = new ArrayList<>();
            result.add(medicine);
            return new Response<>(true, StaticsText.MessageForTest("medicine", "Updated"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<Medicine> DeleteMedicine(UUID medicineId){
        try {
            Medicine medicine=medicineRepo.findMedicineById(medicineId);
            List<MedicineTime> medicineTimes = medicineTimeRepo.findMedicineTimeByMedicineId(medicine.getId());
            medicineTimeRepo.deleteAll(medicineTimes);
            medicine.setIsDeleted(true);
            medicineRepo.save(medicine);
            List<Medicine> result = new ArrayList<>();
            result.add(medicine);
            return new Response<>(true, StaticsText.MessageForTest("medicine", "Deleted"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
    public Response<Medicine> DeleteMedicineTimes(UUID medicineId){
        try {
            Medicine medicine=medicineRepo.findMedicineById(medicineId);
            List<MedicineTime> medicineTimes = medicineTimeRepo.findMedicineTimeByMedicineId(medicine.getId());
            medicineTimeRepo.deleteAll(medicineTimes);
            medicineRepo.save(medicine);
            List<Medicine> result = new ArrayList<>();
            result.add(medicine);
            return new Response<>(true, StaticsText.MessageForTest("medicine Time", "Deleted"), result);
        }catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

}
