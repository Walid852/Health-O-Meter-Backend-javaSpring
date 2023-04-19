package com.example.projectdeploy.Community.Report.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateReport {
    public @NotNull UUID fromUserId;
    public UUID toUserId;
    public UUID postId;
    public UUID commentId;
    public String reason;
}
