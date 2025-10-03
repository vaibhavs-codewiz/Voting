package com.example.project.Service;

import com.example.project.Entity.Candidate;
import com.example.project.Entity.Vote;
import com.example.project.Exception.ResourceNotFoundException;
import com.example.project.Repository.CandidateRepository;
import com.example.project.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private VoteRepository voteRepository;
    CandidateService(CandidateRepository candidateRepository,VoteRepository voteRepository)
    {
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
    }

    public Candidate addCandidate(Candidate candidate)
    {
        return candidateRepository.save(candidate);
    }
    public List<Candidate> getAllCandidates()
    {
        return candidateRepository.findAll();
    }
    public Candidate getCandidateById(int id)
    {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if(candidate == null)
        {
            throw new ResourceNotFoundException("Candidate with id "+id+" not found");
        }
        return candidate;
    }
    public Candidate updateCandidate(int id,Candidate candidate)
    {
        Candidate existingCandidate = getCandidateById(id);

        if(candidate.getName() != null)
            existingCandidate.setName(candidate.getName());
        if(candidate.getParty() != null)
            existingCandidate.setParty(candidate.getParty());
        return candidateRepository.save(existingCandidate);
    }
    public void deleteCandidate(int id)
    {
        Candidate existingCandidate = getCandidateById(id);
        List<Vote> votes = existingCandidate.getVotes();
        for(Vote v : votes)
        {
            v.setCandidate(null);
            voteRepository.save(v);
        }
        existingCandidate.getVotes().clear();
        candidateRepository.delete(existingCandidate);
    }
}
