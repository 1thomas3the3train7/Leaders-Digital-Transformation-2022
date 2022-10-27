package com.example.ideaservice.Model.Idea;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "idea")
public class IdeaShort extends IdeaBase {

}
