package com.main.fastfood.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "commande")
@NoArgsConstructor
@Getter @Setter
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 60)
    private String lastName;

    @Column(name = "prenom", length = 60)
    private String firstName;

    @Column(length = 20, unique = true, nullable = false)
    private String login;

    @Column(name = "mot_de_passe", nullable = false)
    private String password;

    @Column(name = "mail", length = 100, nullable = false)
    private String email;

    @Column(name = "adresse")
    private String address;

    @Column(name = "code_postal", columnDefinition = "smallint(5)")
    private Integer postalCode;

    @Column(name = "ville")
    private String street;

    // ******************************* Relationship ******************************* //

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "commande_burger",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "burger_id")
    )
    private Set<Burger> burgers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "commande_boisson",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "boisson_id")
    )
    private Set<Drink> drinks = new HashSet<>();
}
