package org.hmmm.project.service;

import org.hmmm.project.dto.Comment;
import org.hmmm.project.dto.Movie;
import org.hmmm.project.repository.CommentRepository;
import org.hmmm.project.repository.MovieRepository;
import org.hmmm.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public MovieService(MovieRepository movieRepository,
                        CommentRepository commentRepository,
                        UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void addCommentToMovie(Long movieId, Long userId, String text) {
        Comment comment = Comment.builder()
                .text(text)
                .movieId(movieId)
                .userId(userId)
                .build();
        commentRepository.add(comment);
    }

    public void addMovie(String title) {
        Movie movie = Movie.builder()
                .title(title)
                .build();
        movieRepository.add(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.delete(id);
    }

    public void deleteComment(Long commentId) {
        commentRepository.delete(commentId);
    }
}
