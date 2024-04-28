package com.noteManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
public class Note implements BaseEntity{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private double id;

    @NotBlank
    private String title;

    @NotNull
    @ManyToOne
    private User author;

    @ManyToOne
    private Project project;


    @CreatedDate
    private LocalDate created_at;

    @LastModifiedDate
    private LocalDate updated_at;

}
