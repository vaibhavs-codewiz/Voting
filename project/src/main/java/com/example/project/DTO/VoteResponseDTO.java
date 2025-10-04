package com.example.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {
   private String message;
   private int voterId;
   private int candidateId;
   private boolean success;
}
