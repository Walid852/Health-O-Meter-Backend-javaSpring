package com.example.projectdeploy.Admin.DTO;


import com.example.projectdeploy.Community.Report.dto.ReportAction;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter
public class TakeAction {
    ReportAction action;
    UUID postId;
    UUID commentId;
    UUID userId;
}
