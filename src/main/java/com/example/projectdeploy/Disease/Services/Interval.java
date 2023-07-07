package com.example.projectdeploy.Disease.Services;
import com.example.projectdeploy.Disease.Models.Medicine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Interval {

    public static Date CalculatedEndDate(int NumberOfDays){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        Calendar c = Calendar.getInstance();
        c.setTime(sqlDate); // Using today's date
        c.add(Calendar.DATE,NumberOfDays);// Adding 5 days
        return c.getTime();
    }
    public static List<Date> CalculatedIntervals(Medicine medicineDto){
        List<Date> intervals=new ArrayList<Date>();
        if(medicineDto.getEndDate()==null){
            medicineDto.setEndDate(CalculatedEndDate(medicineDto.getNumberOfDays()));
        }

        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        if(medicineDto.getStartDate()==null){
            medicineDto.setStartDate(sqlDate);
        }
        Date currentDate=medicineDto.getStartDate();
        int hoursBetweenEachTake=24/medicineDto.getNumberOfTakesPerDay();
        int allTakes=medicineDto.getNumberOfTakesPerDay()*medicineDto.getNumberOfDays();
        for(int i=0;i<allTakes;i++){
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.HOUR_OF_DAY,hoursBetweenEachTake);
            intervals.add(cal.getTime());
            currentDate=cal.getTime();
        }
        return intervals;
   }
}
