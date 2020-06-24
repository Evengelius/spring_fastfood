package com.main.fastfood.mapper;

import com.main.fastfood.mapping.UserMapping;
import com.main.fastfood.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     *
     * Entity to Dto
     * (User to UserMapping)
     */
    @Mappings({
            @Mapping(target="name", source="user.name"),
            @Mapping(target="firstname", source="user.firstname"),
            @Mapping(target="username", source="user.username"),
            @Mapping(target="email", source="user.email"),
            @Mapping(target="password", source="user.password")
    })
    UserMapping entityToDto(User user);

    /**
     *
     * EntityList to Dto
     * (List<User> to List<UserMapping>)
     */
    List<UserMapping> entityListToDto(List<User> user);
}
