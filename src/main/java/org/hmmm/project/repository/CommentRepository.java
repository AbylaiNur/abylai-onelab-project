package org.hmmm.project.repository;

import org.hmmm.project.dto.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> findByMovieId(Long movieId);
    void add(Comment comment);
    void delete(Long id);
}
