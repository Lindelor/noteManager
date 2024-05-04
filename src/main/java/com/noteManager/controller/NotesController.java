package com.noteManager.controller;

import com.noteManager.dto.NoteCreateDTO;
import com.noteManager.dto.NoteDTO;
import com.noteManager.dto.NoteUpdateDTO;
import com.noteManager.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    @Autowired
    private NoteService noteService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDTO> index() {
        return noteService.getAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDTO show(@PathVariable Long id) {
        return noteService.findById(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO create(@Valid @RequestBody NoteCreateDTO noteCreateDTO) {
        return noteService.create(noteCreateDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        noteService.remove(id);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDTO update(@PathVariable Long id, @Valid @RequestBody NoteUpdateDTO noteUpdateDTO) {
        return noteService.update(id, noteUpdateDTO);
    }

    @GetMapping(path = "/project/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDTO> showByProjectId(@PathVariable Long id) {
        return noteService.findAllByProjectId(id);
    }
}
