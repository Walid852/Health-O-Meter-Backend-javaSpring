package com.example.projectdeploy.Map.Service;

import com.example.projectdeploy.Map.Model.AddressComponents;
import com.example.projectdeploy.Map.Model.DetailedAddress;
import com.example.projectdeploy.Map.Model.Result;
import com.example.projectdeploy.Map.Model.UserLocation;
import com.example.projectdeploy.Map.Repos.UserLocationRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {
@Autowired
UserLocationRepo userLocationRepo;
   DetailedAddress DetailsForAddressComponents(AddressComponents[] addressComponents){
       DetailedAddress detailedAddress=new DetailedAddress();
       int i=0;
       for (AddressComponents AC:addressComponents) {
           if(i==1){
              detailedAddress.setStreet(AC.getLong_name());
           }
           if(i==2){
               detailedAddress.setCity(AC.getLong_name());
           }
           if(i==3){
               detailedAddress.setGovernment(AC.getLong_name());
           }
           if(i==4){
               detailedAddress.setCountry(AC.getLong_name());

           }
           if(i==5){
               detailedAddress.setPostal_code(AC.getLong_name());
           }

           i++;

       }
       return detailedAddress;
   }
@Transactional
    public UserLocation AddLocation(Result result){
    DetailedAddress detailedAddress=DetailsForAddressComponents(result.getAddress_components());
      UserLocation userLocation=new UserLocation(result.getFormatted_address(),result.getGeometry().getLocation().getLat(),
              result.getGeometry().getLocation().getLng(), detailedAddress.getPostal_code(), detailedAddress.getCountry(),
              detailedAddress.getGovernment(), detailedAddress.getCity(), detailedAddress.getStreet());
      System.out.println(detailedAddress.getPostal_code());
      userLocationRepo.save(userLocation);
      return userLocation;
    }
    public String DeleteLocation(UUID id){
        UserLocation userLocation=userLocationRepo.findUserLocationById(id);
        return "deleted";
    }
    public List<UserLocation> AllLocationBasedOnCity(String city){
       return userLocationRepo.findUserLocationByCity(city);
    }
    public List<UserLocation> AllLocationBasedOnStreet(String street){
        return userLocationRepo.findUserLocationByStreet(street);
    }
    public List<UserLocation> AllLocationBasedOnCountry(String country){
        return userLocationRepo.findUserLocationByCountry(country);
    }
    public List<UserLocation> AllLocationBasedOnGovernment(String government){
        return userLocationRepo.findUserLocationByGovernment(government);
    }
    public List<UserLocation> AllLocationBasedOnPostalCode(String postalCode){
        return userLocationRepo.findUserLocationByPostalCode(postalCode);
    }
}
