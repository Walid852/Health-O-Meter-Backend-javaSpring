package com.example.projectdeploy.Admin.Response;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class PostReports {
    List<String> reportId= new ArrayList<>();
    String PostId;
    String photo;
    String description;
    List<String> Reason= new ArrayList<>();
}
