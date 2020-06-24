package com.main.fastfood.mapping;

import com.main.fastfood.models.Burger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class BurgerMapping {

    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String recipe;
    private Integer quantity;
}
