package com.noteManager.dto;

import java.time.LocalDate;

public class NoteDTO {
    private Long id;
    private String title;
    private String body;
    private String author;
    private String project;
    private LocalDate created_at;
    private LocalDate updated_at;
}
