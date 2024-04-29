package com.noteManager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProjectDTO {
    private Long id;
    private String title;
    private Long authorId;
    private String authorEmail;
    private LocalDate created_at;
    private LocalDate updated_at;
}
