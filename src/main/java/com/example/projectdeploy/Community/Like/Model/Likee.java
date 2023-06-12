package com.example.projectdeploy.Community.Like.Model;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.User.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
public class Likee implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    @JsonIgnore
    private Post post;

    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    @JsonIgnore
    private Comment comment;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
