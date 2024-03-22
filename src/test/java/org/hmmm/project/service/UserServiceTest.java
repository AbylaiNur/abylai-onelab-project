package org.hmmm.project.service;

import org.hmmm.project.dto.Mapper;
import org.hmmm.project.dto.UserDTO;
import org.hmmm.project.entity.User;
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
    private UserRepository userRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setUsername("testUser");
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("testUser", result.getUsername());
    }

    @Test
    void testGetUserByUsername() {
        User user = new User();
        user.setUsername("username");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        User result = userService.getUserByUsername("username");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("username", result.getUsername());
    }

    @Test
    void testAddUser() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        boolean result = userService.addUser("newUser");
        verify(userRepository).save(any(User.class));
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteUser() {
        when(userRepository.existsById(any(Long.class))).thenReturn(true);

        boolean result = userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
        Assertions.assertTrue(result);
    }

    @Test
    void testGetAllUsers() {
        User user = new User();
        user.setUsername("username");
        List<User> users = List.of(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("username");
        List<UserDTO> userDTOs = List.of(userDTO);

        when(userRepository.findAll()).thenReturn(users);
        when(mapper.toUserDTO(any(User.class))).thenReturn(userDTO);

        List<UserDTO> result = userService.getAllUsers();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(userDTOs.size(), result.size());
        Assertions.assertEquals(userDTO.getUsername(), result.get(0).getUsername());
    }
}