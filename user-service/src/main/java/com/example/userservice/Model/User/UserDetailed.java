package com.example.userservice.Model.User;

import com.example.userservice.Model.Idea.IdeaDetailed;
import com.example.userservice.Model.Role.RoleDetailed;
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
@Table(name = "users")
@NoArgsConstructor
public class UserDetailed extends UserBase {
    private String lastName;
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_idea",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "idea_id"))
    private Set<IdeaDetailed> ideas;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_role",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleDetailed> roles;

    public UserDetailed(String email, String password, String firstName) {
        super(email, password, firstName);
    }
}
