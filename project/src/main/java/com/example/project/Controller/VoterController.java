package com.example.project.Controller;

import com.example.project.Entity.Voter;
import com.example.project.Service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
    @Autowired
    public VoterService voterService;
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }
    @PostMapping("/register")
    public ResponseEntity<Voter> registerVoter(Voter voter) {
        Voter registeredVoter = voterService.RegisterVoter(voter);
        return new ResponseEntity<Voter>(registeredVoter,HttpStatus.CREATED);
    }
}
