package com.example.project.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDTO {
    @NotNull(message = "Voter ID cannot be null")
  private int voterId;
    @NotNull(message = "Candidate ID cannot be null")
  private int candidateId;
}
