package org.hmmm.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class CommentDTO {
    private Long id;
    private String text;
    private Long userId;
    private String username;
}
