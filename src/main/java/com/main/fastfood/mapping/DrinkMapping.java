package com.main.fastfood.mapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DrinkMapping {

    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Integer calories;
    private Integer quantity;
}
