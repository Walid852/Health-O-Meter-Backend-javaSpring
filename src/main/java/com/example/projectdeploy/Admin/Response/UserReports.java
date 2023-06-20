package com.example.projectdeploy.Admin.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter @Getter
public class UserReports {
    UUID userId;
    String photo;
    String userName;
    List<String> reason= new ArrayList<>();
}
