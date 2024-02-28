package org.hmmm.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Comment {
    private static long idCount = 0;
    private Long id;
    private String text;
    private User user;
    private Movie movie;

    public static long getNewId() {
        return ++idCount;
    }
}
