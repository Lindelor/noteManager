package com.noteManager.mapper;

import com.noteManager.dto.ProjectCreateDTO;
import com.noteManager.dto.ProjectDTO;
import com.noteManager.dto.ProjectUpdateDTO;
import com.noteManager.model.Project;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class ProjectMapper {
    @Mapping(target = "author.id", source = "authorId")
    public abstract Project map(ProjectCreateDTO projectCreateDTO);
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.email", target = "authorEmail")
    public abstract ProjectDTO map(Project project);
    public abstract void update(ProjectUpdateDTO dto, @MappingTarget Project model);
}
