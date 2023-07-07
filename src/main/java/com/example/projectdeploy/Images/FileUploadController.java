package com.example.projectdeploy.Images;
import com.example.projectdeploy.Shared.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileUploadController {

    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/uploadPhoto")
    public Response<String> uploadFile(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("userId") UUID id,
            @RequestParam("typeForImage") TypeForImage typeForImage
    ) {
        System.out.println("we reached the api");
        System.out.println(file.getName());
        return fileStorageService.storeFile(file,id,typeForImage);

    }

}
