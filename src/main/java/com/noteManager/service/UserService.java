package com.noteManager.service;

import com.noteManager.dto.UserCreateDTO;
import com.noteManager.dto.UserDTO;
import com.noteManager.dto.UserUpdateDTO;
import java.util.List;


public interface UserService {

    List<UserDTO> findAll();

    UserDTO find(Long id);

    UserDTO find(String email);

    UserDTO create(UserCreateDTO userCreateDTO);

    void remove(Long id);

    UserDTO update(Long id, UserUpdateDTO userUpdateDTO);
}
