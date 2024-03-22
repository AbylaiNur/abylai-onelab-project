package org.hmmm.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class MovieDetailsDTO {
    private Long id;
    private String title;
    private String description;
    private float rating;
    private int ratesCount;
    private List<CommentDTO> comments;
    private List<GenreDTO> genres;
}
