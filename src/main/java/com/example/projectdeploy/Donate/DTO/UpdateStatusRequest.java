package com.example.projectdeploy.Donate.DTO;

import com.example.projectdeploy.Donate.Model.Status;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class UpdateStatusRequest {
    UUID donateNotifiedId;
    UUID requstor=null;
    UUID donator=null;
    Status status;
    Date dateOfArrival=null;
}
