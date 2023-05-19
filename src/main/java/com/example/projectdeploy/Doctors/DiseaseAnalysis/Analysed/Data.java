package com.example.projectdeploy.Doctors.DiseaseAnalysis.Analysed;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Data {

    String disease;
    int countOfPeople;
    int cured;
    int notCured;
    int minAge;
    int maxAge;
    int avgAge;
    int countMale;
    int countFemale;
    int minToRecover;
    int maxToRecover;
    int avgToRecover;

}
