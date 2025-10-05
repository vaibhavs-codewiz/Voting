package com.example.project.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {
    @NotBlank(message = "Election name cannot be blank")
    private String electionName;

}
