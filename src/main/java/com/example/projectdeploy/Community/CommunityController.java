package com.example.projectdeploy.Community;


import com.example.projectdeploy.User.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CommunityController {
    @Autowired
    CommunityService communityService;
    @PostMapping(path="/CreateCommunity")
    public @ResponseBody
    Community CreateCommunity(@RequestBody CommunityRequest community)  {
        return communityService.CreateCommunity(community);
    }
    @PatchMapping(path="/UpdateCommunity")
    public @ResponseBody
    Community UpdateCommunity(@RequestParam UUID communityId,@RequestParam String newName) {
        return communityService.UpdateCommunity(communityId,newName);
    }
    @DeleteMapping (path="/DeleteCommunity")
    public @ResponseBody
    String DeleteCommunity(@RequestParam UUID communityId)  {
        return communityService.DeleteCommunity(communityId);
    }
    @GetMapping(path = "/CommunityById")
    public @ResponseBody
    Community CommunityById(@RequestParam UUID communityId) {
        return communityService.CommunityById(communityId);
    }
    @GetMapping(path = "/findCommunityByName")
    public @ResponseBody
    Community findCommunityByName(@RequestParam String name) {
        return communityService.findCommunityByName(name);
    }
    @GetMapping(path = "/Communities")
    public @ResponseBody
    List<Community> findAllCommunities(){
        return communityService.findAllCommunities();
    }
    @GetMapping(path = "/UsersForCommunity")
    public @ResponseBody
    List<User> UsersForCommunity(UUID communityId) {
        return communityService.UsersForCommunity(communityId);
    }
    @GetMapping(path = "/AddUserInCommunity")
    public @ResponseBody
    Community AddUserInCommunity(@RequestParam UUID userId,@RequestParam UUID communityId){
        return communityService.AddUserInCommunity(userId,communityId);
    }
    @DeleteMapping(path = "/DeleteUserInCommunity")
    public @ResponseBody
    Community DeleteUserInCommunity(@RequestParam UUID userId, @RequestParam UUID communityId){
        return communityService.DeleteUserInCommunity(userId,communityId);
    }

}
