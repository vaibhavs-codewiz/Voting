package com.example.project.Service;

import com.example.project.DTO.ElectionResultRequestDTO;
import com.example.project.DTO.ElectionResultResponseDTO;
import com.example.project.Entity.Candidate;
import com.example.project.Entity.ElectionResult;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.CandidateRepository;
import com.example.project.Repository.ElectionResultRepository;
import com.example.project.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionResultService {
 private VoteRepository voteRepository;
 private ElectionResultRepository electionResultRepository;
 private CandidateRepository candidateRepository;

    @Autowired
    public ElectionResultService(VoteRepository voteRepository, ElectionResultRepository electionResultRepository, CandidateRepository candidateRepository)
    {
        this.voteRepository = voteRepository;
        this.electionResultRepository = electionResultRepository;
        this.candidateRepository = candidateRepository;
    }
    public ElectionResult declareResult(String ElectionName)
    {
        Optional<ElectionResult> existingResult = electionResultRepository.findByElectionName(ElectionName);
        if(existingResult.isPresent())
        {
        return existingResult.get();
        }
        if(voteRepository.count() == 0)
        {
            throw new IllegalStateException("No votes have been cast yet");
        }
        List<Candidate> candidates = candidateRepository.findAllByOrderByVoteCountDesc();
        if(candidates.isEmpty())
        {
            throw new ResourceNotFoundException("No candidates found");
        }
        Candidate winner = candidates.get(0);
        int totalVotes = 0;
        for(Candidate candidate : candidates)
        {
            totalVotes += candidate.getVoteCount();
        }
        ElectionResult electionResult = new ElectionResult();
        electionResult.setElectionName(ElectionName);
        electionResult.setWinner(winner);
        electionResult.setTotalVotes(totalVotes);
        return electionResultRepository.save(electionResult);
    }
    public List<ElectionResult> getAllResults()
    {
        return electionResultRepository.findAll();
    }
}
