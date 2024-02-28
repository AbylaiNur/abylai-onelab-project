package org.hmmm.project.repository;

import org.hmmm.project.dto.Rate;

import java.util.List;

public interface RateRepository {
    void addRate(Rate rate);
    void deleteRate(long id);
    Rate getRateById(long id);
    void updateRate(Rate rate);
    List<Rate> getRatesByMovieId(long movieId);
}
