package com.example.project.Service;

import com.example.project.Entity.Candidate;
import com.example.project.Entity.Vote;
import com.example.project.Exception.DuplicateResourceException;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.CandidateRepository;
import com.example.project.Repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.Entity.Voter;

import java.util.List;

@Service
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }
    public Voter RegisterVoter(Voter voter)
    {
        if(voterRepository.existsByEmail(voter.getEmail()))
        {
            throw new DuplicateResourceException("Voter with email "+voter.getEmail()+" already exists");
        }
        return voterRepository.save(voter);
    }
    public List<Voter> getAllVoters()
    {
        return voterRepository.findAll();
    }
    public Voter getVoterById(int id)
    {
        Voter voter =  voterRepository.findById(id).orElse(null);
        if(voter == null)
        {
            throw new ResourceNotFoundException("Voter with id "+id+" not found");
        }
        return voter;
    }
    public Voter updateVoter(int id, Voter voter)
    {
        Voter existingVoter = voterRepository.findById(id).orElse(null);
        if(existingVoter == null)
        {
            throw new ResourceNotFoundException("Voter with id "+id+" not found");
        }
        if(voter.getName() != null)
        existingVoter.setName(voter.getName());
        if(voter.getEmail() != null)
        existingVoter.setEmail(voter.getEmail());
        return voterRepository.save(existingVoter);
    }
    @Transactional
    public void deleteVoter(int id)
    {
        Voter existingVoter = voterRepository.findById(id).orElse(null);
        if(existingVoter == null)
        {
            throw new ResourceNotFoundException("Voter with id "+id+" not found");
        }
        Vote vote = existingVoter.getVote();
        if(vote != null)
        {
            Candidate candidate = vote.getCandidate();
            candidate.setVoteCount((candidate.getVoteCount()-1));
            candidate.getVotes().remove(vote);
            //candidate register krke uska voter delete kro and then check
            //ki vote count decrease hua k nhi ?
//            candidateRepository.save(candidate);
        }
        voterRepository.delete(existingVoter);
    }
}
