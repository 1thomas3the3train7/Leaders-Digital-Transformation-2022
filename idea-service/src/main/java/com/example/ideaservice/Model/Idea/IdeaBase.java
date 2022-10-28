package com.example.ideaservice.Model.Idea;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Table(name = "idea")
public class IdeaBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int likeCount;
    private String tokenIdea;
    @PrePersist
    private void prePersist(){
        this.tokenIdea = UUID.randomUUID().toString();
    }

    public IdeaBase(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
