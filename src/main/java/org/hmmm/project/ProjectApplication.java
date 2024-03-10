package org.hmmm.project;

import org.hmmm.project.service.MovieService;
import org.hmmm.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProjectApplication.class);

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
        logger.info("Adding two users:");
        userService.addUser("user1");
        userService.addUser("user2");
        // printing all users
        logger.info("Printing all users:");
        logger.info("{}", userService.getAllUsers());

        // getting user with id 1
        logger.info("Getting user by id 1:");
        logger.info("{}", userService.getUserById(1L));

        // getting user with username user2
        logger.info("Getting user by username user2:");
        logger.info("{}", userService.getUserByUsername("user2"));

        // adding movie1 and movie2
        logger.info("Adding movie1 and movie2:");
        movieService.addMovie("movie1");
        movieService.addMovie("movie2");

        // printing all movies
        logger.info("Printing all movies:");
        logger.info("{}", movieService.getAllMovies());

        // getting movie by title
        logger.info("Getting movie by title movie1:");
        logger.info("{}", movieService.getMovieByTitle("movie1"));

        // getting movies by containing title
        logger.info("Getting movies by containing title \"mov\":");
        logger.info("{}", movieService.getMoviesByTitle("mov"));

        // adding two rates of user1 and user2 to a movie
        logger.info("Adding two rates of user1 and user2 to movie1 and movie2:");
        movieService.addRateToMovie(1L, 1L, 5);
        movieService.addRateToMovie(2L, 2L, 9);

        // printing top rated movies
        logger.info("Printing top movies sorted by rating:");
        logger.info("{}", movieService.getTopRatedMovies(2));

        // adding two comments of user1 and user2 to a movie
        logger.info("Adding two comments of user1 and user2 to a movie:");
        movieService.addCommentToMovie(1L, 1L, "comment1");
        movieService.addCommentToMovie(1L, 2L, "comment2");
        logger.info("{}", userService.getAllUsers());
        logger.info("{}", movieService.getAllMovies());

        // deleting user1 so that all his comments are deleted
        logger.info("Deleting user1:");
        userService.deleteUser(1L);
        logger.info("{}", userService.getAllUsers());

        // deleting movie1
        logger.info("Deleting movie1:");
        movieService.deleteMovie(1L);
        logger.info("{}", movieService.getAllMovies());

        // testing ValidationAspect
        try {
            logger.info("Attempting to add a user with a null username:");
            userService.addUser(null);
        } catch (IllegalArgumentException e) {
            logger.error("Caught IllegalArgumentException: {}", e.getMessage());
        }

        try {
            logger.info("Attempting to add a movie with a null title:");
            movieService.addMovie(null);
        } catch (IllegalArgumentException e) {
            logger.error("Caught IllegalArgumentException: {}", e.getMessage());
        }

        try {
            logger.info("Attempting to add a comment with a null movieId:");
            movieService.addCommentToMovie(null, 1L, "");
        } catch (IllegalArgumentException e) {
            logger.error("Caught IllegalArgumentException: {}", e.getMessage());
        }
    }
}


