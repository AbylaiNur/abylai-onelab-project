package org.hmmm.project.service;

import org.hmmm.project.dto.User;
import org.hmmm.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User().setId(1L)));

        User result = userService.getUserById(1L);
        Assertions.assertEquals(new User().setId(1L), result);
    }

    @Test
    void testGetUserByUsername() {
        when(userRepository.findByUsername("Mike")).thenReturn(Optional.of(new User().setUsername("Mike")));

        User result = userService.getUserByUsername("Mike");
        Assertions.assertEquals(new User().setUsername("Mike"), result);
    }

    @Test
    void testAddUser() {
        when(userRepository.save(any())).thenReturn(new User());

        userService.addUser("username");
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(
                List.of(new User().setUsername("user1"), new User().setUsername("user2")));

        List<User> result = userService.getAllUsers();
        Assertions.assertEquals(
                List.of(new User().setUsername("user1"), new User().setUsername("user2")),
                result);
    }
}
