package org.hmmm.project;

import org.hmmm.project.entity.Genre;
import org.hmmm.project.entity.Movie;
//import org.hmmm.project.kafka.Producer;
import org.hmmm.project.repository.GenreRepository;
import org.hmmm.project.repository.MovieRepository;
import org.hmmm.project.service.MovieService;
import org.hmmm.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@SpringBootApplication
public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}


