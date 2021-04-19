package com.project.LoginApi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "role")
@Data
public class Role {

    @Id
    @Column(name = "name" , length = 20, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role (String name, String description) {
        this.name = name;
        this.description = description;
    }

}
