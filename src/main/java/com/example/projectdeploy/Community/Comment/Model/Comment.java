package com.example.projectdeploy.Community.Comment.Model;

import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Comment implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @Autowired
    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    @OneToMany(cascade = {CascadeType.ALL})
    List<Comment> replies=new ArrayList<>();

    @NotNull
//    @Size(min = 1)
    String Comment;

    @ManyToOne(cascade = {CascadeType.ALL})
    Post post;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
