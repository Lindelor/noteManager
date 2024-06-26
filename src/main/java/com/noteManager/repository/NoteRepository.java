package com.noteManager.repository;

import com.noteManager.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByProjectIdAndAuthorId(Long projectId, Long authorId);
    List<Note> findAllByAuthorId(Long authorId);
    Optional<Note> findByIdAndAuthorId(Long id, Long authorId);
}
