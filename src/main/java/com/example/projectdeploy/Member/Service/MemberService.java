package com.example.projectdeploy.Member.Service;

import com.example.projectdeploy.Member.Model.Member;
import com.example.projectdeploy.Member.Repo.MemberRepo;
import com.example.projectdeploy.Member.Request.MemberRequest;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import com.example.projectdeploy.User.dto.RegisterDto;
import com.example.projectdeploy.User.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserServices userServices;

    public Member addMember(MemberRequest memberRequest){
        Member member=new Member();
        User user= new User();
        if(userRepo.findById(memberRequest.getUserId()).isPresent()) {
            user = userRepo.findById(memberRequest.getUserId()).get();

            member.setAge(memberRequest.getAge());
            member.setGender(memberRequest.getGender());
            member.setTypeOfMember(memberRequest.getType());
            member.setUser(user);
            member.setName(memberRequest.getName());
            member.setBirthDate(memberRequest.getBirthDate());

            memberRepo.save(member);
        }
        return member;
    }

    public List<Member> getUserMembers(UUID userId){
        return memberRepo.getUserMembers(userId);
    }

    public Member updateMemberInfo(MemberRequest memberRequest){
        Member member=new Member();
        User user = new User();

        if(memberRepo.findById(memberRequest.getMemberId()).isPresent()) {
            member = memberRepo.findById(memberRequest.getMemberId()).get();

            if (userRepo.findById(memberRequest.getUserId()).isPresent()){
                user = userRepo.findById(memberRequest.getUserId()).get();

            if (member.getUser().equals(user)) {
                if (memberRequest.getAge() != 0)
                    member.setAge(memberRequest.getAge());

                if (memberRequest.getName() != null)
                    member.setName(memberRequest.getName());

                if (memberRequest.getType() != null)
                    member.setTypeOfMember(memberRequest.getType());
                memberRepo.save(member);
                }
            }
        }
        return member;
    }

    public Member deleteMember(UUID memberId){
        Member member=new Member();


        if(memberRepo.findById(memberId).isPresent()) {
            member = memberRepo.findById(memberId).get();

            member.setUser(null);
            memberRepo.save(member);
        }
        return member;
    }

    public ResponseEntity<?> memberToUser(RegisterDto registerDto){
        Member member=new Member();
        ResponseEntity<?> s=null;

        if(memberRepo.findById(registerDto.getMemberId()).isPresent()) {
            member = memberRepo.findById(registerDto.getMemberId()).get();
            if(member.getUser().getId().equals(registerDto.getUid())) {
                registerDto.setGender(member.getGender());
                registerDto.setName(member.getName());
                registerDto.setAge(member.getAge());
                s = userServices.register(registerDto);
                deleteMember(registerDto.getMemberId());
            }
        }
        return s;
    }
}
