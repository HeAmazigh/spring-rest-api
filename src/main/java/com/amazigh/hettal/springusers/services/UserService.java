package com.amazigh.hettal.springusers.services;

import com.amazigh.hettal.springusers.domain.User;
import com.amazigh.hettal.springusers.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);

    UserDTO addNewUser(User user);

    void deleteUserById(int id);
    UserDTO updatedUser(User user);

}
