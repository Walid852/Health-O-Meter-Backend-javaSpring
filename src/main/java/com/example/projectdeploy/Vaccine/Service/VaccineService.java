package com.example.projectdeploy.Vaccine.Service;

import com.example.projectdeploy.Member.Model.Member;
import com.example.projectdeploy.Member.Repo.MemberRepo;
import com.example.projectdeploy.Vaccine.Model.Vaccine;
import com.example.projectdeploy.Vaccine.Repo.VaccineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service
public class VaccineService {

    @Autowired
    VaccineRepo vaccineRepo;

    @Autowired
    MemberRepo memberRepo;

    public Map<String, List<Integer>> egyptVaccines(){
        Map<String,List<Integer>> babyVaccine =new HashMap<>();
        List<Integer> afterDays=new ArrayList<>();
        afterDays.add(0); afterDays.add(30); afterDays.add(60);
        afterDays.add(180); afterDays.add(360); afterDays.add(450);
        afterDays.add(540);
        babyVaccine.put("HepB",afterDays);
        afterDays.clear();

        afterDays.add(60);
        afterDays.add(180); afterDays.add(120);
        babyVaccine.put("RV",afterDays);

        afterDays.add(450); afterDays.add(540);
        babyVaccine.put("DTaP",afterDays);

        afterDays.clear();

        afterDays.add(60);
        afterDays.add(180); afterDays.add(120);
        afterDays.add(450); afterDays.add(360);
        babyVaccine.put("Hib",afterDays);

        babyVaccine.put("PCV",afterDays);


        return babyVaccine;
    }

    private Date sqlDatePlusDays(Date date, int days) {
        return Date.valueOf(date.toLocalDate().plusDays(days));
    }

    public List<Vaccine> generateVaccine(UUID memberId){
        List<Vaccine> currnet=new ArrayList<>();
        Member member=memberRepo.findMemberById(memberId);
        Map<String,List<Integer>> babyVaccine=egyptVaccines();

        for (Map.Entry<String,List<Integer>> entry : babyVaccine.entrySet()){
            for(int i :entry.getValue()) {
                Vaccine vaccine = new Vaccine();
                vaccine.setMember(member);
                vaccine.setName(entry.getKey());
                vaccine.setRecommendedVaccineDate(sqlDatePlusDays(member.getBirthDate(), i));
                currnet.add(vaccine);
                vaccineRepo.save(vaccine);
            }
        }
        return currnet;
    }

    @Transactional
    public void deleteBabyVaccine(UUID memberId){
        vaccineRepo.deleteBabyVaccines(memberId);
    }

    @Transactional
    public List<Vaccine> getBabyVaccine(UUID memberId){
        return vaccineRepo.getBabyVaccines(memberId);
    }
}
