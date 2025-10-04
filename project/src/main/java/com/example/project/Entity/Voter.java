package com.example.project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    @JsonIgnore
    private Vote vote;
}
