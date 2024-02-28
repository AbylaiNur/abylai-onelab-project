package org.hmmm.project.repository;

import org.hmmm.project.dto.User;

public interface UserRepository {
    User getUserById(long id);
    User addUser(User user);
}
