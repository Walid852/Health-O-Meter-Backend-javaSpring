package com.example.projectdeploy.Test.Repos;

import com.example.projectdeploy.Test.Models.Renal;
import com.example.projectdeploy.Test.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface RenalRepo extends JpaRepository<Renal,UUID> {

    @Query("select R from Renal R where R.id=?1")
    public Renal renalById(UUID id);

    @Query("select R from Renal R where R.medicalInformation.id=?1 and R.isDeleted=false ")
    public List<Test> renalByMedicalInformationId(UUID medicalInformationId);

    @Query("select R from Renal R where R.medicalInformation.id=?1 and R.isDeleted=true ")
    public List<Test> renalDeletedByMedicalInformationId(UUID medicalInformationId);
    @Query("select R from Renal R ")
    public List<Test> getAllTestForSpecificType();

    @Query("select R from Renal R where R.date>=?1")
    public List<Test> TestByDate(Date date);

}
