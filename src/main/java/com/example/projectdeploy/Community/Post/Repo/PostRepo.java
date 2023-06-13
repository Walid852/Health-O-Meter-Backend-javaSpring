package com.example.projectdeploy.Community.Post.Repo;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Post.Model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {

    @Query("SELECT O from Post O where O.user.id=?1")
    List<Post> getUserPost(UUID userId);

    @Query("SELECT O from Post O where O.community.id=?1")
    List<Post> getCommunityPost(UUID communityId, Pageable pageable);

    @Query("SELECT O from Post O where O.id=?1")
    Post findPostById(UUID postId);

    @Query("select L from Likee L where L.post.id=?1")
    List<Likee> getPostLikes(UUID postId);

    @Query("select C from Comment C where C.post.id=?1")
    List<Comment> getPostComments(UUID postId);

}
