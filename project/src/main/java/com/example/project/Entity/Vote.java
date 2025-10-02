package com.example.project.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;

    // Each Vote belongs to one Voter
    @OneToOne
    @JoinColumn(name = "voter_id")
    private Voter voter;

    // Each Vote belongs to one Candidate
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
