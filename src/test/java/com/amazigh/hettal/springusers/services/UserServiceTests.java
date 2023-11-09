package com.amazigh.hettal.springusers.services;

import com.amazigh.hettal.springusers.domain.User;
import com.amazigh.hettal.springusers.dto.UserDTO;
import com.amazigh.hettal.springusers.exception.EmailAddressAlreadyExistsException;
import com.amazigh.hettal.springusers.repository.UserRepository;
import com.amazigh.hettal.springusers.services.implementation.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    public void setup() {
        //userRepository = Mockito.mock(UserRepository.class);
        //userService = new UserServiceImpl(userRepository);
        user = new User(
                "Hettal",
                "Amazigh",
                "amazigh@gmail.com",
                "password",
                LocalDateTime.now()
        );
    }

    // JUnit test for saveUser
    @DisplayName("JUnit test for saveUser method")
    @Test
    public void givenUserObject_whenSaveUser_thenReturnUserObject() {
        // Given
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userRepository.save(user)).willReturn(user);

        // When
        UserDTO savedUser = userService.addNewUser(user);

        // Then
        Assertions.assertThat(savedUser).isNotNull();
    }

    // JUnit test for saveUser
    @DisplayName("JUnit test for saveUser method throws exception")
    @Test
    public void givenExistingEmail_whenSaveUser_thenThrowsException() {
        // Given
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        // When
        org.junit.jupiter.api.Assertions.assertThrows(EmailAddressAlreadyExistsException.class, () ->{
            userService.addNewUser(user);
        });

        // Then
        verify(userRepository, never()).save(any(User.class));
    }
}
