package com.noteManager.service;

import com.noteManager.dto.UserCreateDTO;
import com.noteManager.dto.UserDTO;
import com.noteManager.dto.UserUpdateDTO;
import com.noteManager.exception.ResourceAlreadyExistException;
import com.noteManager.exception.ResourceNotFoundException;
import com.noteManager.mapper.UserMapper;
import com.noteManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsManager, UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> findAll() {
        var result = userRepository.findAll()
                .stream()
                .map(userMapper::map)
                .toList();

        return result;
    }

    public UserDTO find(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userMapper.map(user);
    }

    public UserDTO find(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userMapper.map(user);
    }

    public UserDTO create(UserCreateDTO userCreateDTO) {
        var maybeUser = userRepository.findByEmail(userCreateDTO.getEmail());
        if (maybeUser.isPresent()) {
            throw new ResourceAlreadyExistException("User with provided email already exist");
        }

        var user = userMapper.map(userCreateDTO);

        userRepository.save(user);

        return userMapper.map(user);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO update(Long id, UserUpdateDTO userUpdateDTO) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userMapper.update(userUpdateDTO, user);
        userRepository.save(user);

        return userMapper.map(user);
    }

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
}
