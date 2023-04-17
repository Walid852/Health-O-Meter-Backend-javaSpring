package com.example.projectdeploy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
    @GetMapping({"/","/home","/status"})
    public ResponseEntity<?> temp() throws Exception {
        return ResponseEntity.ok().body("hello Wold");
    }
}
