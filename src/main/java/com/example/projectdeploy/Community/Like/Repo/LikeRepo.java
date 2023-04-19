package com.example.projectdeploy.Community.Like.Repo;


import com.example.projectdeploy.Community.Like.Model.Likee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeRepo extends JpaRepository<Likee, UUID> {

    @Query("SELECT L from Likee L where L.post.id=?1 and L.user.id=?2")
        Likee getDeletedLike(UUID postId,UUID userId);
}
