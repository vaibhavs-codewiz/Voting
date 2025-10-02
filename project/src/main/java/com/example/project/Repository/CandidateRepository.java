package com.example.project.Repository;

import com.example.project.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    List<Candidate> findAllByOrderByVoteCountDesc();
}
