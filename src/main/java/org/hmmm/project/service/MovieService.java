package org.hmmm.project.service;

import org.hmmm.project.dto.Comment;
import org.hmmm.project.dto.Movie;
import org.hmmm.project.dto.Rate;
import org.hmmm.project.dto.User;
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
        return movieRepository.getAllMovies();
    }

    public List<Comment> getCommentsForMovie(long movieId) {
        return commentRepository.getCommentsByMovieId(movieId);
    }

    public void addCommentToMovie(long movieId, long userId, String text) {
        Movie movie = movieRepository.getMovieById(movieId);
        User user = userRepository.getUserById(userId);
        Comment comment = Comment.builder()
                .id(Comment.getNewId())
                .text(text)
                .movie(movie)
                .user(user)
                .build();
        commentRepository.addComment(comment);
    }

    public void addMovie(String title) {
        Movie movie = Movie.builder()
                .id(Movie.getNewId())
                .title(title)
                .build();
        movieRepository.addMovie(movie);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteMovie(id);
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteComment(commentId);
    }

    // TODO: add rateMovie functionality
//    public void rateMovie(long movieId, int rate, long userId) {
//        Movie movie = movieRepository.getMovieById(movieId);
//        User user = userRepository.getUserById(userId);
//        // TODO: check if user already rated the movie
//        rateRepository.addRate(Rate.builder()
//                .id(Rate.getNewId())
//                .rate(rate)
//                .movie(movie)
//                .user(user)
//                .build());
//    }
    // TODO: fix
//        public List<Movie> getHighestRatingMovies(int count) {
//        List<Movie> movies = movieRepository.getAllMovies();
//        return movies.stream().sorted((m1, m2) -> {
//            double m1Rate = m1.getRates().stream().mapToInt(Rate::getRate).average().orElse(0);
//            double m2Rate = m2.getRates().stream().mapToInt(Rate::getRate).average().orElse(0);
//            return Double.compare(m2Rate, m1Rate);
//        }).limit(count).toList();
//    }
}
