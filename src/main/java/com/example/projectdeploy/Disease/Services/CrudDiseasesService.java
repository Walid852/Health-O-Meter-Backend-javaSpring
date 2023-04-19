package com.example.projectdeploy.Disease.Services;

import com.example.projectdeploy.Disease.DTO.DiseaseDto;
import com.example.projectdeploy.Disease.DTO.MedicineDto;
import com.example.projectdeploy.Disease.Models.Disease;
import com.example.projectdeploy.Disease.Models.Medicine;
import com.example.projectdeploy.Disease.Models.MedicineTime;
import com.example.projectdeploy.Disease.Repos.DiseaseRepo;
import com.example.projectdeploy.Disease.Repos.MedicineRepo;
import com.example.projectdeploy.Disease.Repos.MedicineTimeRepo;
import com.example.projectdeploy.MedicalInformation.MedicalInformation;
import com.example.projectdeploy.MedicalInformation.MedicalInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Disease AddDisease(DiseaseDto creationDisease){
        MedicalInformation medicalInformation=medicalInformationRepo.findMedicalInformationById(creationDisease.getMedicalInformationId());
        if (medicalInformation==null)return null;
        Disease disease=new Disease(medicalInformation,creationDisease.getName()
        ,creationDisease.getDescription(),creationDisease.getStartDate()
        ,creationDisease.getEndDate(), creationDisease.getIsCured());
        diseaseRepo.save(disease);
        return disease;
    }
    public Medicine AddMedicine(MedicineDto M){
        Disease disease=diseaseRepo.findDiseaseById(M.getDiseaseId());
        Medicine medicine=new Medicine(M.getName(),M.getIsNotified(),M.getStartDate(),M.getNumberOfDays(),
                M.getNumberOfTakesPerDay(),disease);
        List<Date> intervals =Interval.CalculatedIntervals(medicine);
        for (Date D:intervals) {
            MedicineTime medicineTime=new MedicineTime(medicine,D);
            medicineTimeRepo.save(medicineTime);
        }
        medicineRepo.save(medicine);
        return medicine;
    }
    public Disease UpdateDisease(DiseaseDto creationDisease){
        Disease disease=diseaseRepo.findDiseaseById(creationDisease.getDiseaseId());
        if(creationDisease.getName()!=null)disease.setName(creationDisease.getName());
        if(creationDisease.getDescription()!=null)disease.setDescription(creationDisease.getDescription());
        if(creationDisease.getStartDate()!=null)disease.setStartDate(creationDisease.getStartDate());
        if(creationDisease.getEndDate()!=null)disease.setEndDate(creationDisease.getEndDate());
        if(creationDisease.getIsCured()!=null)disease.setIsCured(creationDisease.getIsCured());
        return disease;
    }
    public Medicine UpdateMedicine(MedicineDto M){
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
      return medicine;
    }
    public String DeleteDisease(UUID diseaseId){
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
        return "Deleted";
    }
    public String DeleteMedicine(UUID medicineId){
        Medicine medicine=medicineRepo.findMedicineById(medicineId);
        List<MedicineTime> medicineTimes = medicineTimeRepo.findMedicineTimeByMedicineId(medicine.getId());
        medicineTimeRepo.deleteAll(medicineTimes);
        medicine.setIsDeleted(true);
        medicineRepo.save(medicine);
        return "Deleted";
    }
    public String DeleteMedicineTimes(UUID medicineId){
        Medicine medicine=medicineRepo.findMedicineById(medicineId);
        List<MedicineTime> medicineTimes = medicineTimeRepo.findMedicineTimeByMedicineId(medicine.getId());
        medicineTimeRepo.deleteAll(medicineTimes);
        return "Deleted";
    }

}
