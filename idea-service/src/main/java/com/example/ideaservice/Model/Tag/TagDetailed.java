package com.example.ideaservice.Model.Tag;

import com.example.ideaservice.Model.Project.ProjectDetailed;
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
@Table(name = "tag")
public class TagDetailed extends TagBase {
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_and_tag",joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<ProjectDetailed> projects;
}
