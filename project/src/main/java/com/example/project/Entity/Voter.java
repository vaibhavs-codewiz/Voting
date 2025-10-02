package com.example.project.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Getter
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voterId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    private boolean hasVoted;

    // One-to-One with Vote
    @OneToOne(mappedBy = "voter", cascade = CascadeType.ALL)
    private Vote vote;
}
