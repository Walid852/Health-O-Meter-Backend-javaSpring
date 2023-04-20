package com.example.projectdeploy.Vaccine.Repo;

import com.example.projectdeploy.Vaccine.Model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, UUID> {

    @Modifying
    @Query("delete from Vaccine O where O.member.id=?1")
    void deleteBabyVaccines(UUID memberId);

    @Query("SELECT O from Vaccine O where O.member.id=?1")
    List<Vaccine> getBabyVaccines(UUID memberId);
}
