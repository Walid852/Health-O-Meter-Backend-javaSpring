package com.example.projectdeploy.Family.Service;

import com.example.projectdeploy.Family.Model.Family;
import com.example.projectdeploy.Family.Repo.FamilyRepo;
import com.example.projectdeploy.Family.Request.FamilyRequest;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FamilyService {

    @Autowired
    FamilyRepo familyRepo;

    @Autowired
    UserRepo userRepo;

    public Family addFamilyMember(FamilyRequest familyRequest){
        Family family=new Family();
        User first=new User();
        User second=new User();
        if(userRepo.findById(familyRequest.getFirst()).isPresent() && userRepo.findByUsername(familyRequest.getSecond())!=null){
            first=userRepo.findById(familyRequest.getFirst()).get();
            second=userRepo.findByUsername(familyRequest.getSecond());
            if(familyRepo.alreadyFamily(first.getId(),second.getId())==null) {
                family.setFirst(first);
                family.setSecond(second);
                family.setRelationship(familyRequest.getRelationship());
                familyRepo.save(family);
            }
        }
        return family;
    }

    public List<Family> getPendingList(UUID userId){
        return familyRepo.getFamily(false,userId);
    }

    public Family acceptFamilyRequest(UUID familyId, UUID userId){
        Family family = new Family();
        if(familyRepo.findById(familyId).isPresent()){
            family=familyRepo.findById(familyId).get();
            if(family.getSecond().getId()==userId){
                family.setApproved(true);
                familyRepo.save(family);
            }
        }
        return family;
    }

    public Family deleteFamily(UUID familyId, UUID userId){
        Family family = new Family();
        if(familyRepo.findById(familyId).isPresent()){
            family=familyRepo.findById(familyId).get();
            if(family.getSecond().getId()==userId || family.getFirst().getId()==userId ){
                familyRepo.delete(family);
            }
        }
        return family;
    }

    public Family rejectRequest(UUID familyId, UUID userId){
        Family family = new Family();
        if(familyRepo.findById(familyId).isPresent()){
            family=familyRepo.findById(familyId).get();
            if(family.getSecond().getId()==userId){
                deleteFamily(familyId,userId);
            }
        }
        return family;
    }

    public List<Family> getFamilyMembers(UUID userId){
        return familyRepo.getFamily(true,userId);
    }

    public Family updateFamily(FamilyRequest familyRequest){
        Family family = new Family();
        if(familyRepo.findById(familyRequest.getFamilyId()).isPresent()){
            family=familyRepo.findById(familyRequest.getFamilyId()).get();
            if(family.getFirst().getId()==familyRequest.getFirst()){
                if(familyRequest.getRelationship()!=null)
                    family.setRelationship(familyRequest.getRelationship());
                family.setApproved(false);
                familyRepo.save(family);
            }
        }
        return family;
    }
}
