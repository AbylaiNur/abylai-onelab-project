package org.hmmm.project;

import org.hmmm.project.cli.CLI;
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
        CLI cli = context.getBean(CLI.class);
        cli.start();
    }
}


