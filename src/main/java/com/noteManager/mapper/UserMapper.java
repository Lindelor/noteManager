package com.noteManager.mapper;

import com.noteManager.dto.UserCreateDTO;
import com.noteManager.dto.UserDTO;
import com.noteManager.dto.UserUpdateDTO;
import com.noteManager.model.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public abstract User map(UserDTO userCreateDTO);
    public abstract UserDTO map(User user);

    public abstract void update(UserUpdateDTO dto, @MappingTarget User model);

    @BeforeMapping
    public void encryptPassword(UserCreateDTO userCreateDTO) {
        var password = userCreateDTO.getPasswordDigest();
        userCreateDTO.setPasswordDigest(passwordEncoder.encode(password));
    }

    @BeforeMapping
    public void encryptPassword(UserUpdateDTO userUpdateDTO) {
        var password = userUpdateDTO.getPasswordDigest();
        userUpdateDTO.setPasswordDigest(passwordEncoder.encode(password));
    }
}
