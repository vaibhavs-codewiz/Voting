package com.example.project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;

    // Each Vote belongs to one Voter
    @OneToOne
    @JoinColumn(name = "voter_id")
    @JsonIgnore
    private Voter voter;

    // Each Vote belongs to one Candidate
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnore
    private Candidate candidate;
    @JsonProperty
    public int getVoterId()
    {
        return voter != null ? voter.getVoterId() : null; // or throw an exception if preferred
    }
    @JsonProperty
    public int getCandidateId()
    {
        return candidate != null ? candidate.getCandidateId() : null; // or throw an exception if preferred
    }
}
