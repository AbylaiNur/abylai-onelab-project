package org.hmmm.project;

import org.hmmm.project.service.MovieService;
import org.hmmm.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    private final ApplicationContext context;

    @Autowired
    public ProjectApplication(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    public void run(String... arg0) {
        UserService userService = context.getBean(UserService.class);
        MovieService movieService = context.getBean(MovieService.class);

        // adding two users
        System.out.println("Adding two users:");
        userService.addUser("user1");
        userService.addUser("user2");
        System.out.println(userService.getAllUsers());

        // adding a movie
        System.out.println("Adding a movie:");
        movieService.addMovie("movie1");
        System.out.println(movieService.getAllMovies());

        // adding two comments of user1 and user2 to a movie
        System.out.println("Adding two comments of user1 and user2 to a movie:");
        movieService.addCommentToMovie(1L, 1L, "comment1");
        movieService.addCommentToMovie(1L, 2L, "comment2");
        System.out.println(movieService.getAllMovies());

        // deleting user1 so that all his comments are deleted
        System.out.println("Deleting user1 with id 1 so that all his comments are deleted:");
        userService.deleteUser(1L);
        System.out.println(movieService.getAllMovies());

        // deleting movie1
        System.out.println("Deleting movie1:");
        movieService.deleteMovie(1L);
        System.out.println(movieService.getAllMovies());

        // testing ValidationAspect
        try {
            System.out.println("Attempting to add a user with a null username:");
            userService.addUser(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        }

        try {
            System.out.println("Attempting to add a movie with a null title:");
            movieService.addMovie(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        }

        try {
            System.out.println("Attempting to add a comment with a null movieId:");
            movieService.addCommentToMovie(null, 1L, "");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException: " + e.getMessage());
        }
    }
}


