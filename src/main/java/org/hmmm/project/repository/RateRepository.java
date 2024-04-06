package org.hmmm.project.repository;

import org.hmmm.project.entity.Movie;
import org.hmmm.project.entity.Rate;
import org.hmmm.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByMovieAndUser(Movie movie, User user);
}
