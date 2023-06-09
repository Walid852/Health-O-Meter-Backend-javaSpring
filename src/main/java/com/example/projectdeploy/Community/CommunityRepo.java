package com.example.projectdeploy.Community;

import com.example.projectdeploy.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunityRepo extends JpaRepository<Community, UUID> {
    @Query("select C from Community C where C.id=?1")
    public Community findCommunityById(UUID id);
    @Query("select C from Community C where C.name=?1")
    public Community findCommunityByName(String name);
    @Query("select C from Community C ")
    public List<Community> findAllCommunities();
    @Query("select C.users from Community C where C.id=?1")
    public List<User> UsersForCommunity(UUID id);
    @Query("select C from Community C join C.users U where U.id=?1")
    List<Community> getUsersCommunity(UUID userId);

}
