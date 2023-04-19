package com.example.projectdeploy.Community.BookMark.Model;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class BookMark implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    User user;

    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    Post post;

}
