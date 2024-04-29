package com.noteManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Table(name = "projects")
@EntityListeners(AuditingEntityListener.class)
public class Project implements BaseEntity{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Note> notes = new HashSet<>();

    @CreatedDate
    private LocalDate created_at;

    @LastModifiedDate
    private LocalDate updated_at;
}
