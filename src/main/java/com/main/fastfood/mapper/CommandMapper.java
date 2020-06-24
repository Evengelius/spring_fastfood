package com.main.fastfood.mapper;

import com.main.fastfood.mapping.CommandMapping;
import com.main.fastfood.models.Command;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface CommandMapper {
    CommandMapper INSTANCE = Mappers.getMapper(CommandMapper.class);

    /**
     *
     * Entity to Dto
     * (Command to CommandMapping)
     */
    @Mappings({
            @Mapping(target="lastName", source="command.lastName"),
            @Mapping(target="firstName", source="command.firstName"),
            @Mapping(target="login", source="command.login"),
            @Mapping(target="password", source="command.password"),
            @Mapping(target="email", source="command.email"),
            @Mapping(target="address", source="command.address"),
            @Mapping(target="postalCode", source="command.postalCode"),
            @Mapping(target="street", source="command.street"),
            @Mapping(target="burgers", source="command.burgers"),
    })
    CommandMapping entityToDto(Command command);

    /**
     *
     * EntityList to Dto
     * (List<Command> to List<CommandMapping>)
     */
    List<CommandMapping> entityListToDto(List<Command> commands);
}
