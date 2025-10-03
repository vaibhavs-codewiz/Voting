package com.example.project.Repository;

import com.example.project.Entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Integer> {
    boolean existsByEmail(String email);
}