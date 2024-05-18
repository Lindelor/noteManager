package com.noteManager.controller;

import com.noteManager.dto.ProjectCreateDTO;
import com.noteManager.dto.ProjectDTO;
import com.noteManager.dto.ProjectUpdateDTO;
import com.noteManager.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/projects")
public class ProjectsController {
    private ProjectService projectService;

    @Autowired
    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<ProjectDTO>> index() {
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectDTO> show(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<List<ProjectDTO>> findByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(projectService.findAllByCurrentUser(), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        return new ResponseEntity<>(projectService.create(projectCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDTO projectUpdateDTO) {
        return new ResponseEntity<>(projectService.update(id, projectUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        projectService.remove(id);
    }
}
