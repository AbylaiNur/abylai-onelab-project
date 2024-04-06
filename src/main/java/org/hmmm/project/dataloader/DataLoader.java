package org.hmmm.project.dataloader;

import org.hmmm.project.dto.MovieCreateDTO;
import org.hmmm.project.service.MovieService;
import org.hmmm.project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieService movieService;
    private final UserService userService;

    public DataLoader(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Adding users
        userService.addUser("user1");
        userService.addUser("user2");
        userService.addUser("user3");

        // Adding movies
        movieService.addMovie(new MovieCreateDTO().setTitle("The Shawshank Redemption").setDescription("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."));
        movieService.addMovie(new MovieCreateDTO().setTitle("The Godfather").setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."));
        movieService.addMovie(new MovieCreateDTO().setTitle("The Dark Knight").setDescription("When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham."));
        movieService.addMovie(new MovieCreateDTO().setTitle("12 Angry Men").setDescription("A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence."));
        movieService.addMovie(new MovieCreateDTO().setTitle("Schindler's List").setDescription("In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis."));

        // Adding comments
        movieService.commentMovie(1L, 1L, "Incredible movie, must-watch!");
        movieService.commentMovie(2L, 2L, "A masterpiece of storytelling.");

        // Adding rates
        movieService.rateMovie(1L, 1L, 5);
        movieService.rateMovie(2L, 1L, 4);
        movieService.rateMovie(1L, 2L, 5);
        movieService.rateMovie(3L, 3L, 5);
        movieService.rateMovie(4L, 3L, 5);

        // Adding genres
        movieService.addGenreToMovie(1L, "Drama");
        movieService.addGenreToMovie(2L, "Crime");
        movieService.addGenreToMovie(3L, "Action");
        movieService.addGenreToMovie(4L, "Drama");
        movieService.addGenreToMovie(4L, "History");
        movieService.addGenreToMovie(5L, "History");
        movieService.addGenreToMovie(5L, "Drama");
    }
}