package com.example.projectdeploy.Community.Report.Model;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Report implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private User fromUser;


    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private User toUser;

    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private Post post;

    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    private Comment comment;

    @NotNull
    @Size(min = 1)
    String reason;
}
