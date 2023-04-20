package com.example.projectdeploy.Test.Repos;
import com.example.projectdeploy.Test.Models.LiverFunction;
import com.example.projectdeploy.Test.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface LiverFunctionRepo  extends JpaRepository<LiverFunction, UUID> {

    @Query("select L from LiverFunction L where L.id=?1")
    public LiverFunction liverFunctionById(UUID id);

    @Query("select C from LiverFunction C where C.medicalInformation.id=?1 and C.isDeleted=false ")
    public List<Test> liverFunctionByMedicalInformationId(UUID medicalInformationId);

    @Query("select C from LiverFunction C where C.medicalInformation.id=?1 and C.isDeleted=true ")
    public List<Test> liverFunctionDeletedByMedicalInformationId(UUID medicalInformationId);
    @Query("select R from LiverFunction R ")
    public List<Test> getAllTestForSpecificType();

    @Query("select R from LiverFunction R where R.date>=?1 and R.date<=?2")
    public List<Test> TestByDate(Date startDate,Date endDate);
}
