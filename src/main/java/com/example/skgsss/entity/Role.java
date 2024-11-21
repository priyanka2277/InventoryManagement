package com.example.skgsss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Role(Long id,String name) {
        this.name = name;
        this.id=id;

    }
    public Role() {
    }
    @ManyToMany(mappedBy="roles")
    private Set<User> users=new HashSet<>();
}
