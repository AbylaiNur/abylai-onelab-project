package org.hmmm.project.repository.jdbcImpl;

import org.hmmm.project.dto.Comment;
import org.hmmm.project.repository.CommentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRepositoryImpl implements CommentRepository {
    private final JdbcTemplate jdbcTemplate;
    public CommentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> findByMovieId(Long movieId) {
        return jdbcTemplate.query("SELECT * FROM comments WHERE movie_id = ?", new Object[]{movieId}, (rs, rowNum) -> Comment.builder()
                .id(rs.getLong("id"))
                .text(rs.getString("text"))
                .userId(rs.getLong("user_id"))
                .movieId(rs.getLong("movie_id"))
                .build());
    }

    @Override
    public void add(Comment comment) {
        jdbcTemplate.update("INSERT INTO comments (text, user_id, movie_id) VALUES (?, ?, ?)", comment.getText(), comment.getUserId(), comment.getMovieId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM comments WHERE id = ?", id);
    }
}
