package com.example.userservice.Model.Idea;

import com.example.userservice.Model.User.UserDetailed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "idea")
public class IdeaDetailed extends IdeaBase {
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_idea",joinColumns = @JoinColumn(name = "idea_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private UserDetailed user;

    public IdeaDetailed(Long idea_id, String title, String desc) {
        super(idea_id, title, desc);
    }
}
