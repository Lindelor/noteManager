package com.noteManager.controller;

import com.noteManager.dto.ProjectCreateDTO;
import com.noteManager.dto.ProjectDTO;
import com.noteManager.dto.ProjectUpdateDTO;
import com.noteManager.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/projects")
public class ProjectsController {
    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDTO> index() {
        return projectService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO show(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @GetMapping(path = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDTO> findByUserId(@PathVariable Long id) {
        return projectService.findAllByUserId(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO create(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        return projectService.create(projectCreateDTO);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO update(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDTO projectUpdateDTO) {
        return projectService.update(id, projectUpdateDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        projectService.remove(id);
    }
}
