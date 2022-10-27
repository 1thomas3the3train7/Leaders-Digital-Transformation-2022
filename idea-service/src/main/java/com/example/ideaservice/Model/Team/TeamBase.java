package com.example.ideaservice.Model.Team;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@Table(name = "table")
public class TeamBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
