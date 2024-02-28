package org.hmmm.project.repository;

import org.hmmm.project.dto.Movie;
import java.util.List;

public interface MovieRepository {
    List<Movie> getAllMovies();
    Movie getMovieById(long id);
    void addMovie(Movie movie);
    void deleteMovie(long id);
}
