package com.example.projectdeploy.Member.Controller;

import com.example.projectdeploy.Member.Model.Member;
import com.example.projectdeploy.Member.Request.MemberRequest;
import com.example.projectdeploy.Member.Service.MemberService;
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
    Member addMember(@RequestBody MemberRequest memberRequest){
        return memberService.addMember(memberRequest);
    }

    @GetMapping(value = "/getUserMembers")
    public @ResponseBody List<Member> getUserMembers(@RequestParam UUID userId){
        return memberService.getUserMembers(userId);
    }

    @PutMapping(value = "/updateMemberInfo")
    public @ResponseBody Member updateMemberInfo(@RequestBody MemberRequest memberRequest){
        return memberService.updateMemberInfo(memberRequest);
    }

    @PutMapping(value = "/deleteMember")
    public @ResponseBody Member deleteMember(@RequestParam UUID memberId){
        return memberService.deleteMember(memberId);
    }

    @PostMapping(value = "/memberToUser")
    public @ResponseBody ResponseEntity<?> memberToUser(@RequestBody RegisterDto registerDto){
        return memberService.memberToUser(registerDto);
    }

}
