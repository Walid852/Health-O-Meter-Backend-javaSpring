package com.example.projectdeploy.Map.Service;

import com.example.projectdeploy.Map.Model.*;
import com.example.projectdeploy.Map.Repos.UserLocationRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class LocationService {
@Autowired
UserLocationRepo userLocationRepo;
    private static final Object API_KEY = "AIzaSyDQE_OqbesINOGfLOhflK5uGUbVFJXe7L0";
   DetailedAddress DetailsForAddressComponents(AddressComponents[] addressComponents){
       DetailedAddress detailedAddress=new DetailedAddress();
       /*int i=0;
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

       }*/
       for (AddressComponents ac : addressComponents) {
           for (String type : ac.getTypes()) {
               switch (type) {
                   case "administrative_area_level_3":
                       detailedAddress.setStreet(ac.getLong_name());
                       break;
                   case "administrative_area_level_2":
                       detailedAddress.setCity(ac.getLong_name());
                       break;
                   case "administrative_area_level_1":
                       detailedAddress.setGovernment(ac.getLong_name());
                       break;
                   case "country":
                       detailedAddress.setCountry(ac.getLong_name());
                       break;
                   case "postal_code":
                       detailedAddress.setPostal_code(ac.getLong_name());
                       break;
               }
           }
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
    public UserLocation SaveLocation(String latlng){
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/geocode/json")
                .queryParam("key", API_KEY)
                .queryParam("latlng", latlng)
                .build();
        System.out.println(uri.toUriString());
        ResponseEntity<Response> response = new RestTemplate().getForEntity(uri.toUriString(), Response.class);
        Result[] results= Objects.requireNonNull(response.getBody()).getResults();
        System.out.println(Objects.requireNonNull(response.getBody()).getResults().length);
        return AddLocation(results[0]);
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
