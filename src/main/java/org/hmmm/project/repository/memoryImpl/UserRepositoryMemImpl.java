package org.hmmm.project.repository.memoryImpl;

import org.hmmm.project.dto.User;
import org.hmmm.project.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryMemImpl implements UserRepository {
    private List<User> users;

    public UserRepositoryMemImpl() {
        users = new ArrayList<>();
    }

    @Override
    public User getUserById(long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public User addUser(User user) {
        users.add(user);
        return user;
    }
}
