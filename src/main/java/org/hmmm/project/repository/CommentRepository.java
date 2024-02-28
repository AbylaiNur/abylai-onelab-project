package org.hmmm.project.repository;

import org.hmmm.project.dto.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> getCommentsByMovieId(Long movieId);
    void addComment(Comment comment);
    void deleteComment(Long commentId);
}
