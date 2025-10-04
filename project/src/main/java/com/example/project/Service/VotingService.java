package com.example.project.Service;

import com.example.project.Entity.Vote;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Exception.VoteNotAllowedException;
import com.example.project.Repository.CandidateRepository;
import com.example.project.Repository.VoteRepository;
import com.example.project.Repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingService {
 private VoterRepository voterRepository;
 private CandidateRepository candidateRepository;
 private VoteRepository voteRepository;
 public VotingService(VoterRepository voterRepository, CandidateRepository candidateRepository, VoteRepository voteRepository) {
     this.voterRepository = voterRepository;
     this.candidateRepository = candidateRepository;
     this.voteRepository = voteRepository;
 }
 @Transactional
 public Vote castVote(int voterId, int candidateId) {
     var voter = voterRepository.findById(voterId).orElseThrow(() -> new ResourceNotFoundException("Voter not found"));
     var candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
     if (voter.isHasVoted()) {
         throw new VoteNotAllowedException("Voter has already voted");
     }
        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);
//        voteRepository.save(vote);

        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepository.save(candidate);

        voter.setHasVoted(true);
        voter.setVote(vote);
        voterRepository.save(voter);
        return vote;
 }

 public List<Vote> getAllVotes() {
     return voteRepository.findAll();
 }
}
