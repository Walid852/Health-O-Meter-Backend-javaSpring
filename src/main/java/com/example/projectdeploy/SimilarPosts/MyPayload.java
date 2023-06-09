package com.example.projectdeploy.SimilarPosts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class MyPayload {
    @JsonProperty("description")
    private String description;

    @JsonProperty("postid")
    private String uid;



    public MyPayload(
            String description,
            String uid) {
        this.description = description;
        this.uid = uid;

    }

    // Getters and setters
}
