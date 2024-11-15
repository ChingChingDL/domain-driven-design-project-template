package world.snowcrystal.template.domain.common.enums;

public enum SortOrderEnum {

    ASCENDING("ascend"),
    DESCENDING("descend");

    private final String value;


    SortOrderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
