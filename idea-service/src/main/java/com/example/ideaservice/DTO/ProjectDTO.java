package com.example.ideaservice.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
    private String name;
    private TagDTO[] tags;
    private IdeaDTO[] ideas;
    private UserDTO mainUser;
    private UserDTO[] users;
}
