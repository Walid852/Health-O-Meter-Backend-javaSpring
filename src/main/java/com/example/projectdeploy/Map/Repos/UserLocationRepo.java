package com.example.projectdeploy.Map.Repos;

import com.example.projectdeploy.Map.Model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserLocationRepo extends JpaRepository<UserLocation, UUID>{
    @Query("select D from UserLocation D where D.id=?1")
    public UserLocation findUserLocationById(UUID id);
    @Query("select D from UserLocation D where D.city=?1")
    public List<UserLocation> findUserLocationByCity(String city);
    @Query("select D from UserLocation D where D.country=?1")
    public List<UserLocation> findUserLocationByCountry(String country);
    @Query("select D from UserLocation D where D.street=?1")
    public List<UserLocation> findUserLocationByStreet(String street);
    @Query("select D from UserLocation D where D.government=?1")
    public List<UserLocation> findUserLocationByGovernment(String government);
    @Query("select D from UserLocation D where D.postal_code=?1")
    public List<UserLocation> findUserLocationByPostalCode(String postal_code);

}
