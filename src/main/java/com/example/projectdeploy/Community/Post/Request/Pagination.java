package com.example.projectdeploy.Community.Post.Request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter
public class Pagination {
    UUID communityId;
    int page;
    int pageSize;
}
