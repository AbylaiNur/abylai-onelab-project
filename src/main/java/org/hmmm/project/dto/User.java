package org.hmmm.project.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
               cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> comments;
}
