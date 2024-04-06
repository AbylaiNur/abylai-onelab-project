package org.hmmm.project.repository;

import org.hmmm.project.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Transactional(timeout = 10)
    Optional<Genre> findByName(String name);
}
