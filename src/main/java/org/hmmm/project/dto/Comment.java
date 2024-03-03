package org.hmmm.project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class Comment {
    private Long id;
    private String text;
    private Long userId;
    private Long movieId;
    private LocalDateTime createdAt;
}
