package org.hmmm.project.repository;

import org.hmmm.project.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);

    @Transactional(timeout = 10)
    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
    List<Movie> findByTitleContainingIgnoreCase(String title);

    @Transactional(timeout = 10)
    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.name = :genre")
    List<Movie> findByGenre(String genre);
}

/*
CREATE TABLE IF NOT EXISTS MOVIES (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255) NOT NULL,
                                      description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS GENRES (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS MOVIES_GENRES (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        movie_id BIGINT,
                                        genre_id BIGINT,
                                        FOREIGN KEY (movie_id) REFERENCES MOVIES(id) ON DELETE CASCADE,
                                        FOREIGN KEY (genre_id) REFERENCES GENRES(id) ON DELETE CASCADE
);
 */