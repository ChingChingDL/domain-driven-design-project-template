package world.snowcrystal.template.application.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import world.snowcrystal.template.domain.login.command.LoginCommandResponse;
import world.snowcrystal.template.domain.user.dto.command.UserCreateCommand;
import world.snowcrystal.template.domain.user.dto.command.UserLoginCommand;
import world.snowcrystal.template.domain.user.dto.query.UserQueryResponse;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.primitive.Role;

@Mapper(componentModel = "spring")
public interface Assembler {
    @Mapping(source = "value", target = "value")
    Role map(String value);


    @Mapping(source = "userLoginCommand.account", target = "account.value")
    @Mapping(source = "userLoginCommand.password", target = "password.value")
    User toEntity(UserLoginCommand userLoginCommand);

    @Mapping(source = "username", target = "username.value")
    @Mapping(source = "avatar", target = "avatar.value")
    @Mapping(source = "command.role", target = "role")
    @Mapping(source = "account", target = "account.value")
    User toEntity(UserCreateCommand command);


    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "avatar.value", target = "avatar")
    @Mapping(source = "profile.value", target = "profile")
    @Mapping(source = "role.value", target = "role")
    LoginCommandResponse toLoginUserVO(User user);


    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "avatar.value", target = "avatar")
    @Mapping(source = "profile.value", target = "profile")
    @Mapping(source = "role.value", target = "role")
    UserQueryResponse toUserVO(User user);


}
