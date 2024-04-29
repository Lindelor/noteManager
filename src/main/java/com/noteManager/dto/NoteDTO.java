package com.noteManager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class NoteDTO {
    private Long id;
    private String title;
    private String body;
    private Long authorId;
    private String authorEmail;
    private Long projectId;
    private String projectTitle;
    private LocalDate created_at;
    private LocalDate updated_at;
}
