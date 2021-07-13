package com.example.securingweb;

//import android.arch.persistence.room.Entity;
//import lombok.Getter;
//import lombok.Setter;

import javax.persistence.*;

@Entity
//@Getter
//@Setter
@Table(name = "table_userwebapp")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String role;

    @Column(name= "u_group")

    private String group;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}