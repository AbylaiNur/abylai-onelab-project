package org.hmmm.project.service;

import org.hmmm.project.dto.Mapper;
import org.hmmm.project.dto.MovieCreateDTO;
import org.hmmm.project.dto.MovieDTO;
import org.hmmm.project.dto.MovieDetailsDTO;
import org.hmmm.project.entity.*;
import org.hmmm.project.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RateRepository rateRepository;
    private final GenreRepository genreRepository;
    private final Mapper mapper;

    public MovieService(MovieRepository movieRepository,
                        CommentRepository commentRepository,
                        UserRepository userRepository,
                        RateRepository rateRepository,
                        GenreRepository genreRepository,
                        Mapper mapper) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(mapper::toMovieDTO)
                .toList();
    }

    @Transactional
    public boolean commentMovie(Long movieId, Long userId, String text) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (movie != null && user != null) {
            Comment comment = new Comment()
                    .setText(text)
                    .setMovie(movie)
                    .setUser(user);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean rateMovie(Long movieId, Long userId, int rate) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (movie != null && user != null) {
            Rate movieRate = rateRepository.findByMovieAndUser(movie, user)
                    .orElse(new Rate().setMovie(movie).setUser(user));
            movieRate.setRate(rate);
            rateRepository.save(movieRate);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean addMovie(MovieCreateDTO movieCreateDTO) {
        String title = movieCreateDTO.getTitle();
        String description = movieCreateDTO.getDescription();
        Movie movie = new Movie()
                .setTitle(title)
                .setDescription(description);
        movieRepository.save(movie);
        return true;
    }

    @Transactional
    public boolean deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    @Transactional(readOnly = true)
    public List<MovieDTO> getTopRatedMovies(int limit) {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(mapper::toMovieDTO)
                .sorted((m1, m2) -> Float.compare(m2.getRating(), m1.getRating()))
                .limit(limit)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(mapper::toMovieDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<MovieDetailsDTO> getMovieDetails(Long id) {
        return movieRepository.findById(id)
                .map(mapper::toDetailsDTO);
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre).stream()
                .map(mapper::toMovieDTO)
                .toList();
    }

    @Transactional
    public boolean addGenreToMovie(Long movieId, String genre) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null && movie.getGenres().stream().noneMatch(g -> g.getName().equals(genre))) {
            Genre genre1 = genreRepository.findByName(genre)
                    .orElseGet(() -> genreRepository.save(new Genre().setName(genre)));
            movie.getGenres().add(genre1);
            movieRepository.save(movie);
            return true;
        }
        return false;
    }
}
