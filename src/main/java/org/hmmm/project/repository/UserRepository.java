package org.hmmm.project.repository;

import org.hmmm.project.dto.User;

import java.util.List;

public interface UserRepository {
    User findById(Long id);
    void add(User user);
    void delete(Long id);
    List<User> getAllUsers();

}
