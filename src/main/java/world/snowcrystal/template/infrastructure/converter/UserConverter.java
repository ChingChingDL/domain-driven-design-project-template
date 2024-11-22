package world.snowcrystal.template.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.primitive.Role;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;

@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mapping(source = "value", target = "value")
    Role map(String value);

    @Mapping(source = "id", target = "id.value")
    @Mapping(source = "account", target = "account.value")
    @Mapping(source = "username", target = "username.value")
    @Mapping(source = "password", target = "password.value")
    @Mapping(source = "unionId", target = "unionId.value")
    @Mapping(source = "mpOpenId", target = "mpOpenId.value")
    @Mapping(source = "avatar", target = "avatar.value")
    @Mapping(source = "profile", target = "profile.value")
    @Mapping( target = "role",source = "userPO.role")
    User domain(UserPO userPO);

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "account.value", target = "account")
    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "password.value", target = "password")
    @Mapping(source = "unionId.value", target = "unionId")
    @Mapping(source = "mpOpenId.value", target = "mpOpenId")
    @Mapping(source = "avatar.value", target = "avatar")
    @Mapping(source = "profile.value", target = "profile")
    @Mapping(source = "role.value", target = "role")
    UserPO persistence(User user);


}
