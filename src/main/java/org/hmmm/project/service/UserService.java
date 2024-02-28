package org.hmmm.project.service;

import org.hmmm.project.dto.User;
import org.hmmm.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String username) {
        User user = User.builder()
                .id(User.getNewId())
                .username(username)
                .build();
        userRepository.addUser(user);
    }
}
