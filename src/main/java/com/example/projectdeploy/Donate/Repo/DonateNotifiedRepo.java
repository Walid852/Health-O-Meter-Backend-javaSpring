package com.example.projectdeploy.Donate.Repo;

import com.example.projectdeploy.Donate.Model.DonateNotified;
import com.example.projectdeploy.Donate.Model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DonateNotifiedRepo  extends JpaRepository<DonateNotified, UUID>{
    @Query("select D from DonateNotified D where D.id=?1")
    public DonateNotified findDonateById(UUID id);
    @Query("select D from DonateNotified D where D.donate.id=?1")
    public List<DonateNotified> findDonateNotifiedByDonateId(UUID id);
    @Query("select D from DonateNotified D where D.MedicalInformation.id=?1 and  D.status=?2")
    public List<DonateNotified> findDonateNotifiedFoMedicalInformationByStatus(UUID id, Status status);
    @Query("select D from DonateNotified D where D.donate.id=?1 and D.status not in ?2")
    public List<DonateNotified> findDonateNotifiedByDonateIdForRequestor(UUID id,List<Status> SS);
    @Query("select D from DonateNotified D where D.MedicalInformation.id=?1 and  D.status not in ?2")
    public List<DonateNotified> findDonateNotifiedFoMedicalInformation(UUID id,List<Status> SS);
}
