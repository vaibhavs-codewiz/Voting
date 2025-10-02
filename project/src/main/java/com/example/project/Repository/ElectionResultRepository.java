package com.example.project.Repository;

import com.example.project.Entity.ElectionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionResultRepository extends JpaRepository<ElectionResult, Integer> {
     Optional<ElectionResult> findByElectionName(String electionName);
}
