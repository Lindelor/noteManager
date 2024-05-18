package com.noteManager.service;

import com.noteManager.dto.NoteCreateDTO;
import com.noteManager.dto.NoteDTO;
import com.noteManager.dto.NoteUpdateDTO;
import java.util.List;


public interface NoteService {

    List<NoteDTO> findAllByCurrentUser();

    NoteDTO findById(Long id);

    List<NoteDTO> findAllByProjectId(Long id);

    NoteDTO create(NoteCreateDTO noteCreateDTO);

    void remove(Long id);

    NoteDTO update(Long id, NoteUpdateDTO noteUpdateDTO);
}
