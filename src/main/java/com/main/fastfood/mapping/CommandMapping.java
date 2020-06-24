package com.main.fastfood.mapping;

import com.main.fastfood.models.Burger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class CommandMapping {

    private Integer id;
    private String lastName;
    private String firstName;
    private String login;
    private String password;
    private String email;
    private String address;
    private Integer postalCode;
    private String street;

    // Relationship
    private List<Burger> burgers;
}
