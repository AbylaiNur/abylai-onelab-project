package org.hmmm.project.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAddMovie(String title) {
        kafkaTemplate.send("add-movie", title);
    }

    public void sendAddUser(String username) {
        kafkaTemplate.send("add-user", username);
    }

    public void sendAddComment(String message) {
        kafkaTemplate.send("add-comment", message);
    }

    public void sendAddRate(String message) {
        kafkaTemplate.send("add-rate", message);
    }

    public void sendDeleteMovie(String message) {
        kafkaTemplate.send("delete-movie", message);
    }

    public void sendDeleteComment(String message) {
        kafkaTemplate.send("delete-comment", message);
    }

    public void sendDeleteUser(String message) {
        kafkaTemplate.send("delete-user", message);
    }
}
