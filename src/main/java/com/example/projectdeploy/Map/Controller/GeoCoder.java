package com.example.projectdeploy.Map.Controller;

import com.example.projectdeploy.Map.Model.Response;
import com.example.projectdeploy.Map.Model.Result;
import com.example.projectdeploy.Map.Model.UserLocation;
import com.example.projectdeploy.Map.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@ResponseBody
@RequestMapping("/map")
public class GeoCoder {
    @Autowired
    LocationService locationService;

    @GetMapping(value="/getLocation", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    UserLocation getAddress(@RequestParam String latlng){
        System.out.println("Hello");
        return  locationService.SaveLocation(latlng);
    }
    @GetMapping("/AllLocationBasedOnCity")
    public @ResponseBody
    List<UserLocation> findUserLocationByCity(@RequestParam String city){
        return locationService.AllLocationBasedOnCity(city);
    }
    @GetMapping("/AllLocationBasedOnCountry")
    public @ResponseBody
    List<UserLocation> findUserLocationByCountry(@RequestParam String country){
        return locationService.AllLocationBasedOnCountry(country);
    }
    @GetMapping("/AllLocationBasedOnStreet")
    public @ResponseBody
    List<UserLocation> findUserLocationByStreet(@RequestParam String street){
        return locationService.AllLocationBasedOnStreet(street);
    }
    @GetMapping("/AllLocationBasedOnGovernment")
    public @ResponseBody
    List<UserLocation> AllLocationBasedOnGovernment(@RequestParam String government){
        return locationService.AllLocationBasedOnGovernment(government);
    }
    @GetMapping("/AllLocationBasedOnPostalCode")
    public @ResponseBody
    List<UserLocation>  findUserLocationByPostalCode(@RequestParam String postal_code){
        return locationService.AllLocationBasedOnPostalCode(postal_code);
    }
}
