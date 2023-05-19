package com.example.projectdeploy.Doctors.DiseaseAnalysis.Service;

import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.Data;
import com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed.DateReq;
import com.example.projectdeploy.Doctors.DiseaseAnalysis.Repo.DiAnalysisRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiAnalysisService {

    @Autowired
    DiAnalysisRepo diAnalysisRepo;

    public Response<Data> getInsights(DateReq dateReq){
        ArrayList<String> diseases=diAnalysisRepo.getDistinctDisease();
        List<Data> result= new ArrayList<>();
        for (String disease:diseases){
            Data data=new Data();
            data.setDisease(disease);
            data.setCountOfPeople(diAnalysisRepo.getCountOfPeople(disease, dateReq.getFrom(),dateReq.getTo()));
            data.setCured(diAnalysisRepo.getCuredCount(disease, dateReq.getFrom(),dateReq.getTo()));
            data.setNotCured(data.getCountOfPeople()-data.getCured());
            data.setMinAge(diAnalysisRepo.getMinAge(disease, dateReq.getFrom(),dateReq.getTo()));
            data.setMaxAge(diAnalysisRepo.getMaxAge(disease, dateReq.getFrom(),dateReq.getTo()));
            data.setAvgAge(diAnalysisRepo.getAvgAge(disease, dateReq.getFrom(),dateReq.getTo()));
            data.setCountMale(diAnalysisRepo.getCountMale(disease, dateReq.getFrom(),dateReq.getTo()));
            data.setCountFemale(data.getCountOfPeople()-data.getCountMale());
            data.setMinToRecover(diAnalysisRepo.minToRecover(disease, dateReq.getFrom(),dateReq.getTo()));
            data.setMaxToRecover(diAnalysisRepo.maxToRecover(disease, dateReq.getFrom(),dateReq.getTo()));
            data.setAvgToRecover(diAnalysisRepo.avgToRecover(disease, dateReq.getFrom(),dateReq.getTo()));
            result.add(data);
        }
        return new Response<>(true, StaticsText.MessageForTest("Insights", "Returned"), result);
    }
}
