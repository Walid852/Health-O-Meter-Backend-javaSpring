package com.example.projectdeploy.Family.Repo;

import com.example.projectdeploy.Family.Model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FamilyRepo extends JpaRepository<Family, UUID> {

    @Query("select O from Family O where O.isApproved=?1 and (O.first.id=?2 or O.Second.id=?2)")
    List<Family> getFamily(boolean isApproved,UUID userId);

    @Query("select O from Family O where  (O.first.id=?1 and O.Second.id=?2) or (O.first.id=?2 and O.Second.id=?1)")
    List<Family> alreadyFamily(UUID userId1,UUID userId2);
}
