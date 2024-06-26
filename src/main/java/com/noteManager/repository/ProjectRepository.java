package com.noteManager.repository;

import com.noteManager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByAuthorId(Long userId);
    Optional<Project> findByIdAndAuthorId(Long id, Long authorId);
}
