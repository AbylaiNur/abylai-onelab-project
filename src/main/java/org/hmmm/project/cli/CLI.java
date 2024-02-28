package org.hmmm.project.cli;

import org.hmmm.project.dto.Comment;
import org.hmmm.project.dto.Movie;
import org.hmmm.project.service.MovieService;
import org.hmmm.project.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class CLI {

    private final MovieService movieService;
    private final UserService userService;
    private boolean isRunning = true;
    private long cliUserId = 1;
    private static final String helpMessage = "show-movies - показывает список фильмов по id и названию\n" +
            "add-movie <title> - добавляет фильм\n" +
            "delete-movie <id> - удаляет фильм по его id\n" +
            "add-comment <movieId> <text> - добавляет комментарии фильму\n" +
            "show-comments <movieId> - показывает список комментариев фильма по его id\n" +
            "delete-comment <id> - удалить комментарии по его id\n" +
            "help - список команд\n" +
            "exit - выход из программы\n";

    public CLI(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    public void start() {
        userService.addUser("user1");
        Scanner in = new Scanner(System.in);
        System.out.printf(helpMessage);
        while (isRunning) {
            System.out.printf("> ");
            String input = in.nextLine();
            runCommand(input);
        }
        in.close();
    }

    public void runCommand(String input) {
        String[] parts = input.split(" ");

        String command = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        switch (command) {
            case "show-movies":
                showMovies(args);
                break;
            case "add-movie":
                addMovie(args);
                break;
            case "delete-movie":
                deleteMovie(args);
                break;
            case "add-comment":
                addComment(args);
                break;
            case "show-comments":
                showComments(args);
                break;
            case "delete-comment":
                deleteComment(args);
                break;
                // TODO add switch user command
            case "help":
                help();
                break;
            case "exit":
                exit();
                break;
            default:
                System.out.println("Invalid command");
        }
    }


    // TODO: check if objects does not exist
    private void addMovie(String[] args) {
        if (args.length < 1) {
            System.out.println("Invalid command");
            return;
        }
        String title = Arrays.stream(args).reduce((s1, s2) -> s1 + " " + s2).orElse("");
        movieService.addMovie(title);
    }

    private void deleteMovie(String[] args) {
        if (args.length != 1 || !args[0].matches("\\d+")) {
            System.out.println("Invalid command");
            return;
        }
        long id = Long.parseLong(args[0]);
        movieService.deleteMovie(id);
    }

    private void showMovies(String[] args) {
        if (args.length != 0) {
            System.out.println("Invalid command");
            return;
        }
        List<Movie> movies = movieService.getAllMovies();
        for (Movie movie : movies) {
            System.out.printf("id: %d, title: %s\n", movie.getId(), movie.getTitle());
        }
    }

    private void addComment(String[] args) {
        if (args.length < 2 || !args[0].matches("\\d+")) {
            System.out.println("Invalid command");
            return;
        }
        long movieId = Long.parseLong(args[0]);
        String text = Arrays.stream(args).skip(1).reduce((s1, s2) -> s1 + " " + s2).orElse("");
        movieService.addCommentToMovie(movieId, cliUserId, text);
    }

    private void showComments(String[] args) {
        if (args.length != 1 || !args[0].matches("\\d+")) {
            System.out.println("Invalid command");
            return;
        }
        long id = Long.parseLong(args[0]);
        List<Comment> comments = movieService.getCommentsForMovie(id);
        for (Comment comment : comments) {
            System.out.printf("id: %d, text: %s\n", comment.getId(), comment.getText());
        }
    }

    private void deleteComment(String[] args) {
        if (args.length != 1 || !args[0].matches("\\d+")) {
            System.out.println("Invalid command");
            return;
        }
        long id = Long.parseLong(args[0]);
        movieService.deleteComment(id);
    }

    private void help() {
        System.out.printf(helpMessage);
    }

    private void exit() {
        isRunning = false;
    }
}

/*
show movies +
add movie +
//show users
//add user
show comments of a movie by id +
add comment to a movie by id +
delete movie by id +
delete comment by id +
//delete user by id
//show highest rating movies
rate movie by id
exit +
*/