package com.noteManager.service;

import com.noteManager.dto.ProjectCreateDTO;
import com.noteManager.dto.ProjectDTO;
import com.noteManager.dto.ProjectUpdateDTO;
import com.noteManager.exception.ResourceNotFoundException;
import com.noteManager.mapper.ProjectMapper;
import com.noteManager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> findAll() {
        var result = projectRepository.findAll()
                .stream()
                .map(projectMapper::map)
                .toList();

        return result;
    }

    public ProjectDTO findById(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return projectMapper.map(project);
    }

    public ProjectDTO create(ProjectCreateDTO projectCreateDTO) {
        var project = projectMapper.map(projectCreateDTO);

        projectRepository.save(project);

        return projectMapper.map(project);
    }

    public void remove(Long id) {
        projectRepository.deleteById(id);
    }

    public ProjectDTO update(Long id, ProjectUpdateDTO projectUpdateDTO) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        projectMapper.update(projectUpdateDTO, project);
        projectRepository.save(project);

        return projectMapper.map(project);
    }

    public List<ProjectDTO> findAllByUser(Long userId) {
        var result = projectRepository.findAllByUserId(userId)
                .stream()
                .map(projectMapper::map)
                .toList();

        return result;
    }

}
