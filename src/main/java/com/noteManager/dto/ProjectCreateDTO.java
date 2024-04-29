package com.noteManager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectCreateDTO {
    @NotBlank
    private String title;

}
