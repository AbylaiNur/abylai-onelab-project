package org.hmmm.project.kafka;

import org.hmmm.project.service.MovieService;
import org.hmmm.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final MovieService movieService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    public Consumer(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @KafkaListener(topics = "add-movie", groupId = "my-group-id",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAddMovie(String message) {
        String title = message;
        movieService.addMovie(title);
    }

    @KafkaListener(topics = "add-comment", groupId = "my-group-id",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAddComment(String message) {
        String[] parts = message.split(",");
        Long movieId = Long.parseLong(parts[0]);
        Long userId = Long.parseLong(parts[1]);
        String text = parts[2];
        movieService.addCommentToMovie(movieId, userId, text);
    }

    @KafkaListener(topics = "add-rate", groupId = "my-group-id",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAddRate(String message) {
        String[] parts = message.split(",");
        Long movieId = Long.parseLong(parts[0]);
        Long userId = Long.parseLong(parts[1]);
        int rate = Integer.parseInt(parts[2]);
        movieService.addRateToMovie(movieId, userId, rate);
    }

    @KafkaListener(topics = "delete-movie", groupId = "my-group-id",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenDeleteMovie(String message) {
        Long id = Long.parseLong(message);
        movieService.deleteMovie(id);
    }

    @KafkaListener(topics = "delete-comment", groupId = "my-group-id",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenDeleteComment(String message) {
        Long id = Long.parseLong(message);
        movieService.deleteComment(id);
    }

    @KafkaListener(topics = "add-user", groupId = "my-group-id",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAddUser(String message) {
        userService.addUser(message);
    }

    @KafkaListener(topics = "delete-user", groupId = "my-group-id",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenDeleteUser(String message) {
        Long id = Long.parseLong(message);
        userService.deleteUser(id);
    }
}
