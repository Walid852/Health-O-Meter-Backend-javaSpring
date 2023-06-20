package com.example.projectdeploy.Admin.Response;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Setter @Getter
public class PostReports {
    UUID postId;
    String photo;
    String description;
    List<String> reason= new ArrayList<>();
}
