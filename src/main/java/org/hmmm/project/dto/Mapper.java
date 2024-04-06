package org.hmmm.project.dto;

import org.hmmm.project.entity.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public MovieDTO toMovieDTO(Movie movie) {
        return new MovieDTO()
                .setId(movie.getId())
                .setTitle(movie.getTitle())
                .setRating(
                        (float) movie.getRates().stream().reduce(0, (a, b) ->
                                a + b.getRate(), Integer::sum) / Math.max(1, movie.getRates().size()))
                .setRatesCount(movie.getRates().size());
    }

    public MovieDetailsDTO toDetailsDTO(Movie movie) {
        return new MovieDetailsDTO()
                .setId(movie.getId())
                .setTitle(movie.getTitle())
                .setDescription(movie.getDescription())
                .setRating(
                        (float) movie.getRates().stream().reduce(0, (a, b) ->
                                a + b.getRate(), Integer::sum) / Math.max(1, movie.getRates().size()))
                .setRatesCount(movie.getRates().size())
                .setGenres(movie.getGenres().stream().map(this::toGenreDTO)
                        .toList())
                .setComments(movie.getComments().stream().map(this::toCommentDTO)
                        .toList());
    }

    public CommentDTO toCommentDTO(Comment comment) {
        return new CommentDTO()
                .setId(comment.getId())
                .setText(comment.getText())
                .setUserId(comment.getUser().getId())
                .setUsername(comment.getUser().getUsername());
    }

    public GenreDTO toGenreDTO(Genre genre) {
        return new GenreDTO()
                .setId(genre.getId())
                .setName(genre.getName());
    }

    public UserDTO toUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setComments(user.getComments().stream().map(this::toCommentDTO)
                        .toList())
                .setAverageRate(
                        (float) user.getRates().stream().reduce(0, (a, b) ->
                                a + b.getRate(), Integer::sum) / Math.max(1, user.getRates().size()))
                .setRates(user.getRates().stream().map(Rate::getRate)
                        .toList());
    }
}
