package com.example.projectdeploy.Community.BookMark.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BookMarkRequest {

    public UUID userId;
    public UUID postId;
}
