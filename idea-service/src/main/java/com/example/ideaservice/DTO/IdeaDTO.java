package com.example.ideaservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class IdeaDTO {
    private String title;
    private String desc;
    private TagDTO[] tags;
    private UserDTO user;
    private String tokenIdea;
}
