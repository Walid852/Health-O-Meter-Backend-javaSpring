package com.example.projectdeploy.Images;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
class FileStorageService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(Environment env) {
        this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir", "./uploads/files"))
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        String[] fileNameParts = fileName.split("\\.");

        return fileNameParts[fileNameParts.length - 1];
    }

    public Response<String> storeFile(MultipartFile file, UUID id,TypeForImage typeForImage) {
        // Normalize file name
        String fileName =
                new Date().getTime()+id.toString()+ "-file." + getFileExtension(file.getOriginalFilename());

        try {
            // Check if the filename contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException(
                        "Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            if(typeForImage==TypeForImage.User) {
                User user = userRepo.findByUserId(id);
                user.setPhoto(fileName);
                userRepo.save(user);
            }
            else if(typeForImage==TypeForImage.Post) {
                Post post=postRepo.findPostById(id);
                post.setImage(fileName);
                postRepo.save(post);
            }
            else{
                return new Response<>(false, StaticsText.MessageForTest("Photo not Set to ", typeForImage.toString()),new LinkedList<>());
            }
            List<String> filenames=new LinkedList<>();
            filenames.add(fileName);
            return new Response<>(true, StaticsText.MessageForTest("Photo Set to ", typeForImage.toString()),filenames);
        } catch (IOException ex) {
            return new Response<>(false, StaticsText.MessageForTestError());
        }
    }
}
