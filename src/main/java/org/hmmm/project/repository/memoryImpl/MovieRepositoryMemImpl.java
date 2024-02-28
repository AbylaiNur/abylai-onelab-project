package org.hmmm.project.repository.memoryImpl;

import org.hmmm.project.dto.Movie;
import org.hmmm.project.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieRepositoryMemImpl implements MovieRepository {
    private List<Movie> movies;

    public MovieRepositoryMemImpl() {
        movies = new ArrayList<>();
    }


    @Override
    public List<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public Movie getMovieById(long id) {
        return movies.stream().filter(movie -> movie.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    @Override
    public void deleteMovie(long id) {
        movies.removeIf(movie -> movie.getId().equals(id));
    }
}
