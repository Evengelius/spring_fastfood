package com.main.fastfood.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "serveur")
@NoArgsConstructor
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 50)
    private String name;

    @Column(name = "prenom", length = 50)
    private String firstname;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column
    private String email;

    @Column()
    private String password;
}
