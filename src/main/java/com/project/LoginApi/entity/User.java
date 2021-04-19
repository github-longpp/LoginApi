package com.project.LoginApi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "enabled")
    private int enabled;

    @Basic
    @Column(name = "address")
    private String address;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name")
    )
    private Set<Role> roles;

    public User(String email, String password, String phone, String firstName, String lastName, int enabled, String address) {
        this.password = password;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.address = address;
        this.email = email;
    }
}
