package com.noteManager.controller;

import com.noteManager.dto.UserCreateDTO;
import com.noteManager.dto.UserDTO;
import com.noteManager.dto.UserUpdateDTO;
import com.noteManager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> index() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO show(@PathVariable Long id) {
        return userService.find(id);
    }

    @GetMapping(path = "/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO showByEmail(@PathVariable String email) {
        return userService.find(email);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        return userService.create(userCreateDTO);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.update(id, userUpdateDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }
}
