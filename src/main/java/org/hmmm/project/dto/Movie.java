package org.hmmm.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Movie {
    private Long id;
    private String title;
    private List<Comment> comments;
}
