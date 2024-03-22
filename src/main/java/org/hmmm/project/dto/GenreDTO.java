package org.hmmm.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class GenreDTO {
    private Long id;
    private String name;
}
