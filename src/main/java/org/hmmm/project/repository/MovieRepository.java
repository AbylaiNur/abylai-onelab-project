package org.hmmm.project.repository;

import org.hmmm.project.dto.Movie;
import java.util.List;

public interface MovieRepository {
    List<Movie> findAll();
    Movie findById(Long id);
    void add(Movie movie);
    void delete(Long id);
}
