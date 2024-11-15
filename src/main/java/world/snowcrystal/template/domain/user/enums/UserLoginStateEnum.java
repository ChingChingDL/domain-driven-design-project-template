package world.snowcrystal.template.domain.user.enums;

public enum UserLoginStateEnum {

    LOGIN(1);

    private int value;

    UserLoginStateEnum(int value) {
        this.value = value;
    }

    public static UserLoginStateEnum fromString(String loginState) {
        return UserLoginStateEnum.valueOf(loginState);
    }

    public int getValue() {
        return value;
    }

    public static UserLoginStateEnum fromValue(int value) {
        for (UserLoginStateEnum userLoginStateEnum : UserLoginStateEnum.values()) {
            if (userLoginStateEnum.getValue() == value) {
                return userLoginStateEnum;
            }
        }
        return null;

    }

}
