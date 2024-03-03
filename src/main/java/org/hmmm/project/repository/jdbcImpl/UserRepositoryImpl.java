package org.hmmm.project.repository.jdbcImpl;

import org.hmmm.project.dto.User;
import org.hmmm.project.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, (rs, rowNum) -> User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .build());
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO users (username) VALUES (?)", user.getUsername());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }


    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .build());
    }
}
