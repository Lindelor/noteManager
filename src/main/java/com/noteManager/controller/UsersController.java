package com.noteManager.controller;

import com.noteManager.dto.UserCreateDTO;
import com.noteManager.dto.UserDTO;
import com.noteManager.dto.UserUpdateDTO;
import com.noteManager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<UserDTO>> index() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> show(@PathVariable Long id) {
        return new ResponseEntity<>(userService.find(id), HttpStatus.OK);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<UserDTO> showByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.find(email), HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        return new ResponseEntity<>(userService.create(userCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return new ResponseEntity<>(userService.update(id, userUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }
}
