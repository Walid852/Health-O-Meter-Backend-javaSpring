package com.example.projectdeploy.Community;

import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CommunityService {
    @Autowired
    CommunityRepo CommunityRepo;
    @Autowired
    UserRepo userRepo;
    @Transactional
    public Community CreateCommunity(CommunityRequest community) {
        Community communityObject=new Community();
        communityObject.setName(community.name);
        CommunityRepo.save(communityObject);
        return communityObject;
    }
    @Transactional
    public Community UpdateCommunity(UUID communityId, String newName) {
        Community communityObject=CommunityRepo.findCommunityById(communityId);
        communityObject.setName(newName);
        CommunityRepo.save(communityObject);
        return communityObject;
    }
    @Transactional
    public String DeleteCommunity(UUID communityId) {
        Community communityObject=CommunityRepo.findCommunityById(communityId);
        CommunityRepo.delete(communityObject);
        return "community deleted";
    }
    @Transactional
    public Community CommunityById(UUID communityId) {
        return CommunityRepo.findCommunityById(communityId);
    }
    @Transactional
    public Community findCommunityByName(String name)  {
        return CommunityRepo.findCommunityByName(name);
    }
    @Transactional
    public List<Community> findAllCommunities(){
        return CommunityRepo.findAllCommunities();
    }
    @Transactional
    public List<User> UsersForCommunity(UUID communityId){
        return CommunityRepo.UsersForCommunity(communityId);
    }
    @Transactional
    public Community AddUserInCommunity(UUID userId,UUID communityId) {
        Community communityObject=CommunityRepo.findCommunityById(communityId);
        User user=userRepo.findByUserId(userId);
        communityObject.getUsers().add(user);
        CommunityRepo.save(communityObject);
        return communityObject;
    }
    @Transactional
    public Community DeleteUserInCommunity(UUID userId,UUID communityId)  {
        Community communityObject=CommunityRepo.findCommunityById(communityId);
        User user=userRepo.findByUserId(userId);
        communityObject.getUsers().remove(user);
        CommunityRepo.save(communityObject);
        return communityObject;
    }
}
