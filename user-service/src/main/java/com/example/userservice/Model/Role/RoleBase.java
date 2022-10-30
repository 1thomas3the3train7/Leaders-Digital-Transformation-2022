package com.example.userservice.Model.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@Table(name = "role")
@ToString
@NoArgsConstructor
public class RoleBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public RoleBase(String name) {
        this.name = name;
    }
}
