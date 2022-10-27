package com.example.ideaservice.Model.Team;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "team")
public class TeamShort extends TeamBase {

}
