package world.snowcrystal.template.domain.user.primitive;

import lombok.Getter;
import world.snowcrystal.template.domain.user.enums.UserRoleEnum;


@Getter
public class Role {
    UserRoleEnum value;

    public void setValue(String value) {
        this.value = UserRoleEnum.getEnumByValue(value);
    }

    public Role(String role) {
        this.value = UserRoleEnum.getEnumByValue(role);
    }

    private Role(UserRoleEnum roleEnum) {
        this.value = roleEnum;
    }

    public static Role of(String role) {
        return new Role(role);
    }

    public static Role of(UserRoleEnum role) {
        return new Role(role);
    }
}
