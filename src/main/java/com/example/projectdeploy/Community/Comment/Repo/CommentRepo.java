package com.example.projectdeploy.Community.Comment.Repo;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Like.Model.Likee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepo extends JpaRepository<Comment, UUID> {

    @Query("SELECT L from Likee L where L.comment.id=?1")
    List<Likee> getLikesForComment(UUID commentId);

    @Query("select count(C) from Comment C where C.post.id=?1")
    double getNoComment(UUID postId);

    @Query("select c from Comment c join Report r on r.comment.id=c.id")
    List<Comment> getAllComments(Pageable pageable);

    @Modifying
    @Query("DELETE from Comment L where L.id=?1")
    void deletecomment(UUID commentId);

}
