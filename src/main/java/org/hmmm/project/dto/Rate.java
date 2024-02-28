package org.hmmm.project.dto;

import lombok.Builder;

@Builder
public class Rate {
    private static long idCount = 0;
    private Long id;
    private int rate;
    private User user;
    private Movie movie;

    public static long getNewId() {
        return ++idCount;
    }
}
