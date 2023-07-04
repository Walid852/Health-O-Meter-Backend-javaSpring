package com.example.projectdeploy.Member.Service;

import com.example.projectdeploy.Member.Model.Member;
import com.example.projectdeploy.Member.Repo.MemberRepo;
import com.example.projectdeploy.Member.Request.MemberRequest;
import com.example.projectdeploy.Notification.Model.AppNotification;
import com.example.projectdeploy.Notification.Model.ResponseForNotification;
import com.example.projectdeploy.Notification.Model.TypeUrl;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import com.example.projectdeploy.User.dto.RegisterDto;
import com.example.projectdeploy.User.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public Response<Member> addMember(MemberRequest memberRequest){
        try {
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
            List<Member> memberList=new LinkedList<>();
            memberList.add(member);
            return new Response<>(true, StaticsText.MessageForTest("Member", "added"),memberList);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    public Response<Member> getUserMembers(UUID userId){

        try {
            return new Response<>(true, StaticsText.MessageForTest("Member", "retrieved"),memberRepo.getUserMembers(userId));
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }

    }

    public Response<Member> updateMemberInfo(MemberRequest memberRequest){
        try {
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
            List<Member> memberList=new LinkedList<>();
            memberList.add(member);
            return new Response<>(true, StaticsText.MessageForTest("Member", "updated"),memberList);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }


    }

    public Response<Member> deleteMember(UUID memberId){
        try {
            Member member=new Member();
            if(memberRepo.findById(memberId).isPresent()) {
                member = memberRepo.findById(memberId).get();

                member.setUser(null);
                memberRepo.save(member);
            }
            List<Member> memberList=new LinkedList<>();
            memberList.add(member);
            return new Response<>(true, StaticsText.MessageForTest("Member", "deleted"),memberList);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }

    public Response<Member> memberToUser(RegisterDto registerDto){
        try {
            Member member=new Member();
            com.example.projectdeploy.User.Model.Response s=null;
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
            List<Member> memberList=new LinkedList<>();
            memberList.add(member);
            return new Response<>(true, StaticsText.MessageForTest("memberToUser", "Successfully"),memberList);
        }
        catch (Exception e){
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
}
