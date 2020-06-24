package com.main.fastfood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "burger")
@NoArgsConstructor
@Getter @Setter
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", nullable = false)
    private String name;

    @Column(name = "prix", columnDefinition = "decimal(4,2)", nullable = false)
    private Double price;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "recette", columnDefinition = "text")
    private String recipe;

    @Column(name = "quantite_stock", columnDefinition = "tinyint(3) unsigned")
    private Integer quantity;

    // ******************************* Relationship ******************************* //

    @ManyToMany(mappedBy = "burgers")
    @JsonIgnore // Forbid to over loop in the ManyToMany
    private Set<Command> commands;
}
