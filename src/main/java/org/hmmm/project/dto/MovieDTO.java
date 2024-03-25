package org.hmmm.project.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class MovieDTO {
    private Long id;
    private String title;
    private float rating;
    private int ratesCount;
}
