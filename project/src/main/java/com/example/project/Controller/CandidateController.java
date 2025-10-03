package com.example.project.Controller;

import com.example.project.Entity.Candidate;
import com.example.project.Service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    @PostMapping("/add")
    public ResponseEntity<Candidate> addCandidate(@Valid @RequestBody Candidate candidate)
    {
        Candidate newCandidate = candidateService.addCandidate(candidate);
        return ResponseEntity.status(201).body(newCandidate);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable int id) {
        Candidate candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }
    @GetMapping()
    public ResponseEntity<java.util.List<Candidate>> getAllCandidates() {
        java.util.List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable int id, @RequestBody Candidate candidate) {
        Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
        return ResponseEntity.ok(updatedCandidate);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable int id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok("Candidate with id " + id + " deleted successfully");
    }
}
