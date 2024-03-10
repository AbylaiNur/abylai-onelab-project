package org.hmmm.project.service;

import org.hmmm.project.dto.Comment;
import org.hmmm.project.dto.Movie;
import org.hmmm.project.dto.Rate;
import org.hmmm.project.dto.User;
import org.hmmm.project.repository.CommentRepository;
import org.hmmm.project.repository.MovieRepository;
import org.hmmm.project.repository.RateRepository;
import org.hmmm.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;
    @Mock
    CommentRepository commentRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    RateRepository rateRepository;
    @InjectMocks
    MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMovies() {
        when(movieRepository.findAll())
                .thenReturn(List.of(new Movie().setTitle("m1"), new Movie().setTitle("m2")));
        List<Movie> result = movieService.getAllMovies();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testAddCommentToMovie() {
        Long movieId = 1L, userId = 1L;
        Movie movie = new Movie();
        User user = new User();

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        movieService.addCommentToMovie(movieId, userId, "Nice movie!");

        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void testAddRateToMovie() {
        Long movieId = 1L, userId = 1L;
        Movie movie = new Movie();
        User user = new User();

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(rateRepository.save(any(Rate.class))).thenReturn(new Rate().setRate(5));

        movieService.addRateToMovie(movieId, userId, 5);
        verify(rateRepository).save(any(Rate.class));
    }

    @Test
    void testAddMovie() {
        Movie movie = new Movie().setTitle("New Movie");
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        movieService.addMovie("New Movie");

        verify(movieRepository).save(any(Movie.class));
        Assertions.assertEquals("New Movie", movie.getTitle());
    }

    @Test
    void testDeleteMovie() {
        movieService.deleteMovie(1L);
        verify(movieRepository).deleteById(1L);
    }

    @Test
    void testDeleteComment() {
        movieService.deleteComment(1L);
        verify(commentRepository).deleteById(1L);
    }

    @Test
    void testGetTopRatedMovies() {
        when(movieRepository.findTopRatedMovies(any())).thenReturn(List.of(new Movie(), new Movie()));
        List<Movie> result = movieService.getTopRatedMovies(2);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testGetMovieByTitle() {
        Optional<Movie> movieOptional = Optional.of(new Movie().setTitle("title"));
        when(movieRepository.findByTitle("title")).thenReturn(movieOptional);

        Movie result = movieService.getMovieByTitle("title");
        Assertions.assertEquals("title", result.getTitle());
    }

    @Test
    void testGetMoviesByTitle() {
        when(movieRepository.findByTitleContainingIgnoreCase("of")).thenReturn(
                List.of(new Movie().setTitle("Lord of rings")));
        List<Movie> result = movieService.getMoviesByTitle("of");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Lord of rings", result.get(0).getTitle());
    }
}
