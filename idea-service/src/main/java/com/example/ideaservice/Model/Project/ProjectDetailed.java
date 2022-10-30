package com.example.ideaservice.Model.Project;

import com.example.ideaservice.Model.Idea.IdeaDetailed;
import com.example.ideaservice.Model.Tag.TagDetailed;
import com.example.ideaservice.Model.Team.TeamDetailed;
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
@Table(name = "project")
public class ProjectDetailed extends ProjectBase {
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id",referencedColumnName = "id")
    private TeamDetailed team;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_project",joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserDetailed> users;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_and_idea",joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "idea_id"))
    private Set<IdeaDetailed> ideas;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_and_tag",joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagDetailed> tags;

    public ProjectDetailed(String name) {
        super(name);
    }
}
