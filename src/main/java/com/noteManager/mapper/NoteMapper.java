package com.noteManager.mapper;

import com.noteManager.dto.NoteCreateDTO;
import com.noteManager.dto.NoteDTO;
import com.noteManager.dto.NoteUpdateDTO;
import com.noteManager.model.Note;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class NoteMapper {
    @Mapping(target = "project.id", source = "projectId")
    public abstract Note map(NoteCreateDTO noteCreateDTO);

    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "projectTitle", source = "project.title")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorEmail", source = "author.email")
    public abstract NoteDTO map(Note note);

    @Mapping(target = "project.id", source = "projectId")
    public abstract void update(NoteUpdateDTO dto, @MappingTarget Note model);
}
