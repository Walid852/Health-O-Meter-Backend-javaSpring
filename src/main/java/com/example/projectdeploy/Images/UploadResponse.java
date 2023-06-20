package com.example.projectdeploy.Images;

import java.util.UUID;

public class UploadResponse {
    private final String fileName;
    private final UUID userId;

    public UploadResponse(String fileName,UUID userId) {
        this.fileName = fileName;
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

}
