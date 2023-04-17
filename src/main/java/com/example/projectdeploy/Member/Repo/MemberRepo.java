package com.example.projectdeploy.Member.Repo;

import com.example.projectdeploy.Member.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepo extends JpaRepository<Member, UUID> {

    @Query("select O from Member O where O.user.id=?1")
    List<Member> getUserMembers(UUID userId);

    @Query("select O from Member O where O.id=?1")
    Member findMemberById(UUID memberId);
}
