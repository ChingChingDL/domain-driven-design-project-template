package world.snowcrystal.template.domain.common.enums;

public enum SessionAttributeEnum {
    LOGIN_USER("loginUser"),
    USER_LANGUAGE("userLanguage");


    private final String value;

    SessionAttributeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
