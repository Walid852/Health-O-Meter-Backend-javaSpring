package com.example.projectdeploy.SimilarPosts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    @JsonProperty("results")
    private ArrayList<JsonParsing> results;

    public ArrayList<JsonParsing>  getResults() {
        return results;
    }


}

