package com.example.userservice.Model.Idea;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Table(name = "idea")
public class IdeaBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idea_id;
    private String title;
    private String desc;

    public IdeaBase(Long idea_id, String title, String desc) {
        this.idea_id = idea_id;
        this.title = title;
        this.desc = desc;
    }
}
