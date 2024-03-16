package org.hmmm.project.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic addMovieTopic() {
        return new NewTopic("add-movie", 1, (short) 1);
    }

    @Bean
    public NewTopic addCommentTopic() {
        return new NewTopic("add-comment", 1, (short) 1);
    }

    @Bean
    public NewTopic addRateTopic() {
        return new NewTopic("add-rate", 1, (short) 1);
    }

    @Bean
    public NewTopic deleteMovieTopic() {
        return new NewTopic("delete-movie", 1, (short) 1);
    }

    @Bean
    public NewTopic deleteCommentTopic() {
        return new NewTopic("delete-comment", 1, (short) 1);
    }

    @Bean
    public NewTopic addUserTopic() {
        return new NewTopic("add-user", 1, (short) 1);
    }

    @Bean
    public NewTopic deleteRateTopic() {
        return new NewTopic("delete-rate", 1, (short) 1);
    }

    @Bean
    public NewTopic deleteUserTopic() {
        return new NewTopic("delete-user", 1, (short) 1);
    }
}
