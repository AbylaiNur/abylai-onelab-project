package org.hmmm.project.service;

import org.hmmm.project.dto.Mapper;
import org.hmmm.project.dto.UserDTO;
import org.hmmm.project.entity.User;
import org.hmmm.project.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Mapper mapper;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new Mapper();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public boolean addUser(String username) {
        if (userRepository.findByUsername(username).isEmpty()) {
            userRepository.save(new User().setUsername(username));
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(mapper::toUserDTO)
                .toList();
    }
}
