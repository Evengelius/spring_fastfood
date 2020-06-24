package com.main.fastfood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "boisson")
@NoArgsConstructor
@Getter @Setter
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", nullable = false)
    private String name;

    @Column(name = "prix", columnDefinition = "decimal(4,2)", nullable = false)
    private Double price;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "calories", columnDefinition = "int(11) unsigned NOT NULL")
    private Integer calories;

    @Column(name = "quantite_stock", columnDefinition = "tinyint(3) unsigned")
    private Integer quantity;

    // ******************************* Relationship ******************************* //

    @ManyToMany(mappedBy = "drinks")
    @JsonIgnore // Forbid to over loop in the ManyToMany
    private Set<Command> commands;
}
