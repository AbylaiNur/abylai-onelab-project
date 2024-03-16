package org.hmmm.project.service;

import org.hmmm.project.dto.Comment;
import org.hmmm.project.dto.Movie;
import org.hmmm.project.dto.Rate;
import org.hmmm.project.dto.User;
import org.hmmm.project.repository.CommentRepository;
import org.hmmm.project.repository.MovieRepository;
import org.hmmm.project.repository.RateRepository;
import org.hmmm.project.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RateRepository rateRepository;

    public MovieService(MovieRepository movieRepository,
                        CommentRepository commentRepository,
                        UserRepository userRepository,
                        RateRepository rateRepository) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional
    public void addCommentToMovie(Long movieId, Long userId, String text) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Comment comment = new Comment()
                .setText(text)
                .setMovie(movie)
                .setUser(user);
        commentRepository.save(comment);
    }

    @Transactional
    public void addRateToMovie(Long movieId, Long userId, int rate) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Rate rate1 = new Rate()
                .setRate(rate)
                .setMovie(movie)
                .setUser(user);
        rateRepository.save(rate1);
    }

    @Transactional
    public void addMovie(String title) {
        Movie movie = new Movie()
                .setTitle(title);
        movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional(readOnly = true)
    public List<Movie> getTopRatedMovies(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return movieRepository.findTopRatedMovies(pageable);
    }

    @Transactional(readOnly = true)
    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
}
