package org.hmmm.project.controller;

import org.hmmm.project.dto.UserDTO;
import org.hmmm.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testAddUser() throws Exception {
        Mockito.when(userService.addUser("newUser")).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                        .param("username", "newUser"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddUserFailure() throws Exception {
        Mockito.when(userService.addUser("existingUser")).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                        .param("username", "existingUser"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteUser() throws Exception {
        Mockito.when(userService.deleteUser(1L)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/delete")
                        .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUserFailure() throws Exception {
        Mockito.when(userService.deleteUser(2L)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/delete")
                        .param("id", "2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<UserDTO> users = List.of(
                new UserDTO().setId(1L).setUsername("userOne"),
                new UserDTO().setId(2L).setUsername("userTwo"));
        Mockito.when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(users.size()))
                .andExpect(jsonPath("$[0].username").value("userOne"))
                .andExpect(jsonPath("$[1].username").value("userTwo"));
    }
}
