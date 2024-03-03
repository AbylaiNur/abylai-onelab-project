package org.hmmm.project.service;

import org.hmmm.project.dto.User;
import org.hmmm.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(String username) {
        User user = User.builder()
                .username(username)
                .build();
        userRepository.add(user);
    }
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
