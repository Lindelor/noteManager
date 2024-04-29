package com.noteManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoteUpdateDTO {
    @NotBlank
    private String title;
    private String body;
    @NotNull
    private Long projectId;
}
