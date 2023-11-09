package com.amazigh.hettal.springusers.services;

import com.amazigh.hettal.springusers.domain.User;
import com.amazigh.hettal.springusers.dto.UserDTO;
import com.amazigh.hettal.springusers.repository.UserRepository;
import com.amazigh.hettal.springusers.services.implementation.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserServiceTests {

    private UserRepository userRepository;

    private UserService userService;
    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    // JUnit test for saveUser
    @DisplayName("JUnit test for saveUser")
    @Test
    public void givenUserObject_whenSaveUser_ReturnUserObject() {
        // Given
        User user = new User(
                "Hettal",
                "Amazigh",
                "amazigh@gmail.com",
                "password",
                LocalDateTime.now()
        );

        BDDMockito.given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        BDDMockito.given(userRepository.save(user)).willReturn(user);

        // When
        UserDTO savedUser = userService.addNewUser(user);

        // Then
        Assertions.assertThat(savedUser).isNotNull();
    }
}
