package com.example.ideaservice.Model.Team;

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
@Table(name = "team")
public class TeamDetailed extends TeamBase {
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    @OneToOne(mappedBy = "team")// TODO Lazy????
    private ProjectDetailed project;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_team",joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserDetailed> users;

    public TeamDetailed(String name) {
        super(name);
    }
}
