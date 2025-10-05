package com.example.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ElectionResultResponseDTO {
    private String electionName;
    private int totalVotes;
    private int winnerId;
    private int winnerVotes;
}
