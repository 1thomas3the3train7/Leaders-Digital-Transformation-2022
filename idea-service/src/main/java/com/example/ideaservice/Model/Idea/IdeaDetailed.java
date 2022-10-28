package com.example.ideaservice.Model.Idea;

import com.example.ideaservice.Model.Project.ProjectDetailed;
import com.example.ideaservice.Model.User.UserDetailed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "idea")
public class IdeaDetailed extends IdeaBase{
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_idea",joinColumns = @JoinColumn(name = "idea_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private UserDetailed user;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_and_idea",joinColumns = @JoinColumn(name = "idea_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<ProjectDetailed> projects;

    public IdeaDetailed(String title, String description) {
        super(title, description);
    }
}
