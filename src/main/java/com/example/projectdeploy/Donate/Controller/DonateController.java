package com.example.projectdeploy.Donate.Controller;

import com.example.projectdeploy.Donate.DTO.DonateRequest;
import com.example.projectdeploy.Donate.DTO.Resultt;
import com.example.projectdeploy.Donate.Services.CreateDonate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/Donate")
public class DonateController {
    @Autowired
    CreateDonate createDonate;
    @PostMapping(path="/AddDonate")
    public @ResponseBody
    Resultt AddDonate(@RequestBody DonateRequest donateRequest){
        return createDonate.CreateDonate(donateRequest);
    }
}
