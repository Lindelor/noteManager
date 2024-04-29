package com.noteManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements BaseEntity{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Note> notes = new HashSet<>();

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String passwordDigest;

    @CreatedDate
    private LocalDate created_at;

    @LastModifiedDate
    private LocalDate updated_at;
}
