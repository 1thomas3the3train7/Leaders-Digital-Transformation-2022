package com.example.ideaservice.Model.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class CommentDetailed extends CommentBase {
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
}
