package com.example.projectdeploy.Member.Controller;

import com.example.projectdeploy.Member.Model.Member;
import com.example.projectdeploy.Member.Request.MemberRequest;
import com.example.projectdeploy.Member.Service.MemberService;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.User.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping(value = "/addMember")
    public @ResponseBody
    Response<Member> addMember(@RequestBody MemberRequest memberRequest){
        return memberService.addMember(memberRequest);
    }

    @GetMapping(value = "/getUserMembers")
    public @ResponseBody Response<Member> getUserMembers(@RequestParam UUID userId){
        return memberService.getUserMembers(userId);
    }

    @PutMapping(value = "/updateMemberInfo")
    public @ResponseBody Response<Member> updateMemberInfo(@RequestBody MemberRequest memberRequest){
        return memberService.updateMemberInfo(memberRequest);
    }

    @DeleteMapping(value = "/deleteMember")
    public @ResponseBody Response<Member> deleteMember(@RequestParam UUID memberId){
        return memberService.deleteMember(memberId);
    }

    @PostMapping(value = "/memberToUser")
    public @ResponseBody Response<Member> memberToUser(@RequestBody RegisterDto registerDto){
        return memberService.memberToUser(registerDto);
    }

}
