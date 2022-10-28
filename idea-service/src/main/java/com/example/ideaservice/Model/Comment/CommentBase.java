package com.example.ideaservice.Model.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class CommentBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contains;
    private int likeCount;
}
