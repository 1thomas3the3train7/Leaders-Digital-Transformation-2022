package com.example.ideaservice.Model.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Table(name = "project")
public class ProjectBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tokenProject;
    @PrePersist
    private void prePersist(){
        this.tokenProject = UUID.randomUUID().toString();
    }

    public ProjectBase(String name) {
        this.name = name;
    }
}
