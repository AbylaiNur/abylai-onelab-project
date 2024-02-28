package org.hmmm.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Movie {
    private static long idCount = 0;
    private Long id;
    private String title;

    public static long getNewId() {
        return ++idCount;
    }
}
