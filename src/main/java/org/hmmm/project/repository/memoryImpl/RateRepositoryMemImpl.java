package org.hmmm.project.repository.memoryImpl;

import org.hmmm.project.dto.Rate;
import org.hmmm.project.repository.RateRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RateRepositoryMemImpl implements RateRepository {

    @Override
    public void addRate(Rate rate) {

    }

    @Override
    public void deleteRate(long id) {

    }

    @Override
    public Rate getRateById(long id) {
        return null;
    }

    @Override
    public void updateRate(Rate rate) {

    }

    @Override
    public List<Rate> getRatesByMovieId(long movieId) {
        return null;
    }
}
