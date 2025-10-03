package com.example.project.Controller;

import com.example.project.Entity.Voter;
import com.example.project.Service.VoterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter) {
        Voter registeredVoter = voterService.RegisterVoter(voter);
        return new ResponseEntity<Voter>(registeredVoter,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable int id)
    {
        Voter voter = voterService.getVoterById(id);
        return new ResponseEntity<>(voter,HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Voter>> getAllVoters()
    {
        List<Voter> voters = voterService.getAllVoters();
        return new ResponseEntity<>(voters,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable int id,@RequestBody Voter updatedVoter)
    {
        Voter voter = voterService.updateVoter(id,updatedVoter);
        return new ResponseEntity<>(voter,HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleterVoter(@PathVariable int id)
    {
        voterService.deleteVoter(id);
        return new ResponseEntity<>("Voter with id "+id+" deleted successfully",HttpStatus.OK);
    }
}
