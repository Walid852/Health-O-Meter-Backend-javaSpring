package com.example.projectdeploy.Admin.Response;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter @Getter
public class CommentReports {
    UUID commentId;
    String comment;
    List<String> reason= new ArrayList<>();
}
