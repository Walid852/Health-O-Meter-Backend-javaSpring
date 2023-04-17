package com.example.projectdeploy.Map.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("results")
    private Result[] results;

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }
}
