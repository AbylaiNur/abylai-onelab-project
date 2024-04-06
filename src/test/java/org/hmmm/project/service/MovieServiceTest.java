package org.hmmm.project.service;

import org.hmmm.project.dto.Mapper;
import org.hmmm.project.dto.MovieCreateDTO;
import org.hmmm.project.dto.MovieDTO;
import org.hmmm.project.dto.MovieDetailsDTO;
import org.hmmm.project.entity.*;
import org.hmmm.project.repository.*;
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
    private MovieRepository movieRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RateRepository rateRepository;
    @Mock
    private GenreRepository genreRepository;
    @Mock
    private Mapper mapper;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMovies() {
        when(movieRepository.findAll()).thenReturn(List.of(new Movie()));
        when(mapper.toMovieDTO(any(Movie.class))).thenReturn(new MovieDTO());

        List<MovieDTO> result = movieService.getAllMovies();

        Assertions.assertFalse(result.isEmpty());
        verify(movieRepository).findAll();
        verify(mapper, atLeastOnce()).toMovieDTO(any(Movie.class));
    }

    @Test
    void testCommentMovie() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(new Movie()));

        boolean result = movieService.commentMovie(1L, 1L, "Great movie!");

        Assertions.assertTrue(result);
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void testRateMovie() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(new Movie()));
        when(rateRepository.findByMovieAndUser(any(Movie.class), any(User.class)))
                .thenReturn(Optional.empty());

        boolean result = movieService.rateMovie(1L, 1L, 5);

        Assertions.assertTrue(result);
        verify(rateRepository).save(any(Rate.class));
    }

    @Test
    void testAddMovie() {
        MovieCreateDTO movieCreateDTO = new MovieCreateDTO();
        movieCreateDTO.setTitle("New Movie");
        movieCreateDTO.setDescription("Description of the new movie.");

        movieService.addMovie(movieCreateDTO);

        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void testDeleteMovie() {
        when(movieRepository.existsById(anyLong())).thenReturn(true);

        boolean result = movieService.deleteMovie(1L);

        Assertions.assertTrue(result);
        verify(movieRepository).deleteById(1L);
    }

    @Test
    void testSearchMoviesByTitle() {
        when(movieRepository.findByTitleContainingIgnoreCase(anyString())).thenReturn(List.of(new Movie()));
        when(mapper.toMovieDTO(any(Movie.class))).thenReturn(new MovieDTO());

        List<MovieDTO> result = movieService.searchMoviesByTitle("Test");

        Assertions.assertFalse(result.isEmpty());
        verify(movieRepository).findByTitleContainingIgnoreCase("Test");
        verify(mapper, atLeastOnce()).toMovieDTO(any(Movie.class));
    }

    @Test
    void testGetMovieDetails() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(new Movie()));
        when(mapper.toDetailsDTO(any(Movie.class))).thenReturn(new MovieDetailsDTO());

        Optional<MovieDetailsDTO> result = movieService.getMovieDetails(1L);

        Assertions.assertTrue(result.isPresent());
        verify(movieRepository).findById(1L);
        verify(mapper).toDetailsDTO(any(Movie.class));
    }

    @Test
    void testAddGenreToMovie() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(new Movie()));
        when(genreRepository.findByName(anyString())).thenReturn(Optional.empty());

        boolean result = movieService.addGenreToMovie(1L, "Drama");

        Assertions.assertTrue(result);
        verify(movieRepository).save(any(Movie.class));
        verify(genreRepository).save(any(Genre.class));
    }

    @Test
    void testGetMoviesByGenre() {
        when(genreRepository.findByName(anyString())).thenReturn(Optional.of(new Genre()));
        when(movieRepository.findByGenre(anyString())).thenReturn(List.of(new Movie()));
        when(mapper.toMovieDTO(any(Movie.class))).thenReturn(new MovieDTO());

        List<MovieDTO> result = movieService.getMoviesByGenre("Comedy");

        Assertions.assertFalse(result.isEmpty());
        verify(movieRepository).findByGenre("Comedy");
        verify(mapper, atLeastOnce()).toMovieDTO(any(Movie.class));
    }
}