package com.example.projectdeploy.Community.Post.Model;

import com.example.projectdeploy.Community.Community;
import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class Post implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    //
  /*  @Autowired
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Likee> likes =new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Comment> comments= new ArrayList<>();*/
//////
    @NotNull
    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    User user;

    @ManyToOne(cascade = {CascadeType.ALL})
    @Autowired
    Community community;

    private String image;

    @NotNull
    private String description;


    private String File;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;

    /*public void addLike(Likee likeMe){
        likes.add(likeMe);
    }
    public void addComment(Comment userComment){
        comments.add(userComment);
    }*/

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }


}
