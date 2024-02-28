package org.hmmm.project.repository.memoryImpl;

import org.hmmm.project.dto.Comment;
import org.hmmm.project.repository.CommentRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentRepositoryMemImpl implements CommentRepository {
    private List<Comment> comments;

    public CommentRepositoryMemImpl() {
        comments = new ArrayList<>();
    }

    @Override
    public List<Comment> getCommentsByMovieId(Long movieId) {
        return comments.stream().filter(comment -> comment.getMovie().getId().equals(movieId)).toList();
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        comments.removeIf(comment -> comment.getId().equals(commentId));
    }
}
