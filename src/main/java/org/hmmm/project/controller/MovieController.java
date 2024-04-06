package org.hmmm.project.controller;

import org.hmmm.project.dto.MovieCreateDTO;
import org.hmmm.project.dto.MovieDTO;
import org.hmmm.project.dto.MovieDetailsDTO;
import org.hmmm.project.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addMovie(@RequestBody MovieCreateDTO movieCreateDTO) {
        if (movieService.addMovie(movieCreateDTO)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<MovieDetailsDTO> getMovieDetails(@PathVariable Long id) {
        return movieService.getMovieDetails(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/rate")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> rateMovie(@RequestParam Long movieId, @RequestParam Long userId, @RequestParam int rate) {
        if (movieService.rateMovie(movieId, userId, rate)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCommentToMovie(@RequestParam Long movieId, @RequestParam Long userId, @RequestParam String text) {
        if (movieService.commentMovie(movieId, userId, text)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        if (movieService.deleteMovie(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/top")
    public List<MovieDTO> getTopRatedMovies(@RequestParam int count) {
        return movieService.getTopRatedMovies(count);
    }

    @GetMapping("/search")
    public List<MovieDTO> searchMovies(@RequestParam String title) {
        return movieService.searchMoviesByTitle(title);
    }

    @GetMapping("/search/genre")
    public List<MovieDTO> searchMoviesByGenre(@RequestParam String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    @PostMapping("/add/genre")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addGenreToMovie(@RequestParam Long movieId, @RequestParam String genre) {
        if (movieService.addGenreToMovie(movieId, genre)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
