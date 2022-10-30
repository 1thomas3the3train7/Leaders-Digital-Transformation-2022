package com.example.ideaservice.Model.User;

import com.example.ideaservice.Model.Idea.IdeaDetailed;
import com.example.ideaservice.Model.Project.ProjectDetailed;
import com.example.ideaservice.Model.Team.TeamDetailed;
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
@Table(name = "users")
public class UserDetailed extends UserBase {
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_idea",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "idea_id"))
    private Set<IdeaDetailed> ideas;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_project",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<ProjectDetailed> projects;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_team",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<TeamDetailed> teams;

    public UserDetailed(String email, String name) {
        super(email, name);
    }
}
