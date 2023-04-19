package com.example.projectdeploy.Community;

import com.example.projectdeploy.User.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class Community implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;

    @NotNull
    @Size(min = 3,max = 25)
    @Column(unique=true)
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    List<User> users = new ArrayList<>();





}
