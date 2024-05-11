package com.noteManager.service;

import com.noteManager.dto.NoteCreateDTO;
import com.noteManager.dto.NoteDTO;
import com.noteManager.dto.NoteUpdateDTO;
import com.noteManager.exception.ResourceNotFoundException;
import com.noteManager.mapper.NoteMapper;
import com.noteManager.model.Note;
import com.noteManager.repository.NoteRepository;
import com.noteManager.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserUtils userUtils;

    public List<NoteDTO> getAll() {
        var result = noteRepository.findAllByAuthorId(userUtils.getCurrentUser().getId())
                .stream()
                .map(noteMapper::map)
                .toList();

        return result;
    }

    public NoteDTO findById(Long id) {
        var note = noteRepository.findByIdAndAuthorId(id, userUtils.getCurrentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        return noteMapper.map(note);
    }

    public List<NoteDTO> findAllByProjectId(Long id) {
        var result = noteRepository.findAllByProjectIdAndAuthorId(id, userUtils.getCurrentUser().getId())
                .stream()
                .map(noteMapper::map)
                .toList();

        return result;
    }

    public NoteDTO create(NoteCreateDTO noteCreateDTO) {
        var note = noteMapper.map(noteCreateDTO);
        note.setAuthor(userUtils.getCurrentUser());
        noteRepository.save(note);
        return noteMapper.map(note);
    }

    public void remove(Long id) {
        noteRepository.findByIdAndAuthorId(id, userUtils.getCurrentUser().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
        noteRepository.deleteById(id);
    }

    public NoteDTO update(Long id, NoteUpdateDTO noteUpdateDTO) {
        var note = noteRepository.findByIdAndAuthorId(id, userUtils.getCurrentUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        noteMapper.update(noteUpdateDTO, note);

        noteRepository.save(note);

        return noteMapper.map(note);
    }
}
