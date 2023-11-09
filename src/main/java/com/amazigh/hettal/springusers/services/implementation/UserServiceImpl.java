package com.amazigh.hettal.springusers.services.implementation;

import com.amazigh.hettal.springusers.domain.User;
import com.amazigh.hettal.springusers.dto.UserDTO;
import com.amazigh.hettal.springusers.dtomapper.UserDTOMapper;
import com.amazigh.hettal.springusers.repository.UserRepository;
import com.amazigh.hettal.springusers.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTOMapper::fromUser)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserDTOMapper::fromUser).orElse(null);
    }

    @Override
    public UserDTO addNewUser(User user) {
        return UserDTOMapper.fromUser(userRepository.save(user));
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
