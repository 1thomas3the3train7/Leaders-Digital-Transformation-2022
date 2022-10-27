package com.example.userservice.Model.Idea;

import com.example.userservice.Model.User.UserShort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "idea")
public class IdeaShort extends IdeaBase {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_and_idea",joinColumns = @JoinColumn(name = "idea_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private UserShort user;
}
