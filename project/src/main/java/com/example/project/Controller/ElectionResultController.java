package com.example.project.Controller;

import com.example.project.DTO.ElectionResultRequestDTO;
import com.example.project.DTO.ElectionResultResponseDTO;
import com.example.project.Entity.ElectionResult;
import com.example.project.Service.ElectionResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/electionResults")
@CrossOrigin
public class ElectionResultController {
 private ElectionResultService electionResultService;
    @Autowired
    public ElectionResultController(ElectionResultService electionResultService) {
        this.electionResultService = electionResultService;
    }
    @PutMapping("/declare")
    public ResponseEntity<ElectionResultResponseDTO> declareResult(@Valid @RequestBody ElectionResultRequestDTO electionResultRequestDTO)
    {
        ElectionResult electionResult = electionResultService.declareResult(electionResultRequestDTO.getElectionName());
        ElectionResultResponseDTO electionResultResponseDTO = new ElectionResultResponseDTO(electionResult.getElectionName(), electionResult.getTotalVotes(),electionResult.getWinnerId(),electionResult.getWinner().getVoteCount());
        return ResponseEntity.status(201).body(electionResultResponseDTO);
    }
    @GetMapping("/getAllResults")
    public ResponseEntity<java.util.List<ElectionResult>> getAllResults() {
        java.util.List<ElectionResult> electionResults = electionResultService.getAllResults();
        return ResponseEntity.ok(electionResults);
    }
}
