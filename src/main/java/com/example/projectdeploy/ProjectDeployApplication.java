package com.example.projectdeploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@SpringBootApplication
public class ProjectDeployApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectDeployApplication.class, args);
    }

}
