package com.noteManager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectUpdateDTO {
    @NotBlank
    private String title;
}
