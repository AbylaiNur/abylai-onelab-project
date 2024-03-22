package org.hmmm.project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hmmm.project.entity.Comment;
import org.hmmm.project.entity.Movie;
import org.hmmm.project.entity.User;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* org.hmmm.project.service.*.add*(..))")
    public void validateAddMethods(JoinPoint joinPoint) throws IllegalArgumentException {
        for (Object arg : joinPoint.getArgs()) {
            if (arg == null) {
                throw new IllegalArgumentException("Method argument cannot be null");
            }
            if (arg instanceof User && ((User) arg).getUsername().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty");
            }
            if (arg instanceof Movie && ((Movie) arg).getTitle().isEmpty()) {
                throw new IllegalArgumentException("Movie title cannot be empty");
            }
            if (arg instanceof Comment) {
                Comment comment = (Comment) arg;
                if (comment.getText().isEmpty()) {
                    throw new IllegalArgumentException("Comment text cannot be empty");
                }
                if (comment.getUser() == null || comment.getMovie() == null) {
                    throw new IllegalArgumentException("Comment must have a valid user and movie ID");
                }
            }
        }
    }
}