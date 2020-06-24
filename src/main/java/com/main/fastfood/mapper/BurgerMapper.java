package com.main.fastfood.mapper;

import com.main.fastfood.mapping.BurgerMapping;
import com.main.fastfood.mapping.CommandMapping;
import com.main.fastfood.models.Burger;
import com.main.fastfood.models.Command;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface BurgerMapper {
    BurgerMapper INSTANCE = Mappers.getMapper(BurgerMapper.class);

    /**
     *
     * Entity to Dto
     * (Burger to BurgerMapping)
     */
    @Mappings({
            @Mapping(target="name", source="burger.name"),
            @Mapping(target="price", source="burger.price"),
            @Mapping(target="description", source="burger.description"),
            @Mapping(target="recipe", source="burger.recipe"),
            @Mapping(target="quantity", source="burger.quantity"),
    })
    BurgerMapping entityToDto(Burger burger);

    /**
     *
     * EntityList to Dto
     * (List<Burger> to List<BurgerMapping>)
     */
    List<BurgerMapping> entityListToDto(List<Burger> burgers);
}
