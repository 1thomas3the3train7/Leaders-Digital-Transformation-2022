package com.example.userservice.Model.Role;

import com.example.userservice.Model.User.UserDetailed;
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
@Table(name = "role")
@NoArgsConstructor
public class RoleDetailed extends RoleBase {
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_role",joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserDetailed> users;

    public RoleDetailed(String name) {
        super(name);
    }
}
