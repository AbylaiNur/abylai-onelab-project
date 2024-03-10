package org.hmmm.project.dto;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER,
               cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER,
               cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Rate> rates;
}
