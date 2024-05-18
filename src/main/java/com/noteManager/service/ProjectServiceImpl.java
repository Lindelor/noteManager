package com.noteManager.service;

import com.noteManager.dto.ProjectCreateDTO;
import com.noteManager.dto.ProjectDTO;
import com.noteManager.dto.ProjectUpdateDTO;
import com.noteManager.exception.ResourceNotFoundException;
import com.noteManager.mapper.ProjectMapper;
import com.noteManager.repository.ProjectRepository;
import com.noteManager.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectMapper projectMapper;

    private ProjectRepository projectRepository;

    private UserUtils userUtils;

    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper, ProjectRepository projectRepository, UserUtils userUtils) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
        this.userUtils = userUtils;
    }

    @Override
    public List<ProjectDTO> findAll() {
        var result = projectRepository.findAllByAuthorId(userUtils.getCurrentUser().getId())
                .stream()
                .map(projectMapper::map)
                .toList();

        return result;
    }

    @Override
    public ProjectDTO findById(Long id) {
        var project = projectRepository.findByIdAndAuthorId(id, userUtils.getCurrentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return projectMapper.map(project);
    }

    @Override
    public ProjectDTO create(ProjectCreateDTO projectCreateDTO) {
        var author = userUtils.getCurrentUser();
        var project = projectMapper.map(projectCreateDTO);
        project.setAuthor(author);
        projectRepository.save(project);

        return projectMapper.map(project);
    }

    @Override
    public void remove(Long id) {
        var project = projectRepository.findByIdAndAuthorId(id, userUtils.getCurrentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectDTO update(Long id, ProjectUpdateDTO projectUpdateDTO) {
        var project = projectRepository.findByIdAndAuthorId(id, userUtils.getCurrentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        projectMapper.update(projectUpdateDTO, project);
        projectRepository.save(project);

        return projectMapper.map(project);
    }

    @Override
    public List<ProjectDTO> findAllByCurrentUser() {
        var result = projectRepository.findAllByAuthorId(userUtils.getCurrentUser().getId())
                .stream()
                .map(projectMapper::map)
                .toList();

        return result;
    }
}
