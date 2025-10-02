package com.example.project.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class ElectionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resultId;
    @NotBlank(message = "Election name is mandatory")
    private String electionName;

    private int totalVotes;

    // One-to-One: winner candidate
    @OneToOne
    @JoinColumn(name = "winner_id")
    private Candidate winner;
}
