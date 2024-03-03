package org.hmmm.project.repository.jdbcImpl;

import org.hmmm.project.dto.Comment;
import org.hmmm.project.dto.Movie;
import org.hmmm.project.repository.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieRepositoryImpl implements MovieRepository {
    private final JdbcTemplate jdbcTemplate;
    public MovieRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM movies", (rs, rowNum) -> Movie.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .build());
        movies.forEach(movie -> {
            List<Comment> comments = findCommentsByMovieId(movie.getId());
            movie.setComments(comments);
        });
        return movies;
    }

    @Override
    public Movie findById(Long id) {
        return null;
    }

    @Override
    public void add(Movie movie) {
        jdbcTemplate.update("INSERT INTO movies (title) VALUES (?)", movie.getTitle());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM movies WHERE id = ?", id);
    }

    private List<Comment> findCommentsByMovieId(Long movieId) {
        return jdbcTemplate.query("SELECT * FROM comments WHERE movie_id = ?", new Object[]{movieId}, (rs, rowNum) -> Comment.builder()
                .id(rs.getLong("id"))
                .text(rs.getString("text"))
                .userId(rs.getLong("user_id"))
                .movieId(rs.getLong("movie_id"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build());
    }
}
