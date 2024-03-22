package org.hmmm.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class UserDTO {
    private Long id;
    private String username;
    private List<CommentDTO> comments;
    private double averageRate;
    private List<Integer> rates;
}
