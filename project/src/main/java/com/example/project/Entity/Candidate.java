package com.example.project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Party is mandatory")
    private String party;

    private int voteCount;

    // One Candidate can have many votes
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vote> votes;

    // One-to-One with ElectionResult (Candidate can be winner in one result)
    @OneToOne(mappedBy = "winner")
    private ElectionResult electionResult;
}
