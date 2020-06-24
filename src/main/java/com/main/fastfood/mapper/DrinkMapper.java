package com.main.fastfood.mapper;

import com.main.fastfood.mapping.DrinkMapping;
import com.main.fastfood.models.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface DrinkMapper {
    DrinkMapper INSTANCE = Mappers.getMapper(DrinkMapper.class);

    /**
     *
     * Entity to Dto
     * (Drink to DrinkMapping)
     */
    @Mappings({
            @Mapping(target="name", source="drink.name"),
            @Mapping(target="price", source="drink.price"),
            @Mapping(target="description", source="drink.description"),
            @Mapping(target="calories", source="drink.calories"),
            @Mapping(target="quantity", source="drink.quantity"),
    })
    DrinkMapping entityToDto(Drink drink);

    /**
     *
     * EntityList to Dto
     * (List<Burger> to List<BurgerMapping>)
     */
    List<DrinkMapping> entityListToDto(List<Drink> drinks);
}
