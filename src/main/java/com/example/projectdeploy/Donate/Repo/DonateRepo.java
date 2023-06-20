package com.example.projectdeploy.Donate.Repo;

import com.example.projectdeploy.Donate.Model.Donate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DonateRepo extends JpaRepository<Donate, UUID> {
    @Query("select D from Donate D where D.id=?1")
    public Donate findDonateById(UUID id);
    @Query("select D from Donate D where D.RequestorMedicalInformation.id=?1")
    public List<Donate> findMyDonateByMedicalInformationId(UUID id);
    @Query("select D from Donate D where D.RequestorMedicalInformation.id=?1 and D.isDone=?2")
    public Donate findDonateByIsDone(UUID id,boolean isDone);


}
