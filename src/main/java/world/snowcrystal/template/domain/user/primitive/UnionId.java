package world.snowcrystal.template.domain.user.primitive;

import lombok.Value;

@Value
public class UnionId {
    String value;

    public UnionId(String value) {
        this.value = value;
    }

    public static UnionId of(String value) {
        return new UnionId(value);
    }
}
