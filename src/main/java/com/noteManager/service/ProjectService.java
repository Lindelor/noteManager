package com.noteManager.service;

import com.noteManager.dto.ProjectCreateDTO;
import com.noteManager.dto.ProjectDTO;
import com.noteManager.dto.ProjectUpdateDTO;
import java.util.List;

public interface ProjectService {
    List<ProjectDTO> findAll();

    ProjectDTO findById(Long id);

    ProjectDTO create(ProjectCreateDTO projectCreateDTO);

    void remove(Long id);

    ProjectDTO update(Long id, ProjectUpdateDTO projectUpdateDTO);

    List<ProjectDTO> findAllByCurrentUser();

}
