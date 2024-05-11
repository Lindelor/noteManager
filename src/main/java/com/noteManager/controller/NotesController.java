package com.noteManager.controller;

import com.noteManager.dto.NoteCreateDTO;
import com.noteManager.dto.NoteDTO;
import com.noteManager.dto.NoteUpdateDTO;
import com.noteManager.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    @Autowired
    private NoteService noteService;

    @GetMapping(path = "")
    public ResponseEntity<List<NoteDTO>> index() {
        return new ResponseEntity<>(noteService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NoteDTO> show(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<NoteDTO> create(@Valid @RequestBody NoteCreateDTO noteCreateDTO) {
        return new ResponseEntity<>(noteService.create(noteCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        noteService.remove(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<NoteDTO> update(@PathVariable Long id, @Valid @RequestBody NoteUpdateDTO noteUpdateDTO) {
        return new ResponseEntity<>(noteService.update(id, noteUpdateDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/project/{id}")
    public ResponseEntity<List<NoteDTO>> showByProjectId(@PathVariable Long id) {
        return new ResponseEntity<>(noteService.findAllByProjectId(id), HttpStatus.OK);
    }
}
