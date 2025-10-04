package com.example.project.Controller;

import com.example.project.DTO.VoteRequestDTO;
import com.example.project.DTO.VoteResponseDTO;
import com.example.project.Entity.Vote;
import com.example.project.Service.VotingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {

    private VotingService votingService;
    @Autowired
    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }
   @PostMapping("/cast")
    public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequestDTO)
    {
        Vote vote = votingService.castVote(voteRequestDTO.getVoterId(), voteRequestDTO.getCandidateId());
        VoteResponseDTO voteResponseDTO = new VoteResponseDTO("Vote cast successfully", vote.getVoterId(),vote.getCandidateId() ,true);
        return ResponseEntity.status(201).body(voteResponseDTO);
    }
    @GetMapping("/getAllVotes")
    public ResponseEntity<java.util.List<Vote>> getAllVotes()
    {
        java.util.List<Vote> votes = votingService.getAllVotes();
        return ResponseEntity.ok(votes);
    }
}
