package world.snowcrystal.template.domain.user.primitive;

import jakarta.validation.ValidationException;
import lombok.Value;

@Value
public class UnionId {
    String value;

    public UnionId(String value) {
        if (value == null || value.isBlank()) {
            throw new ValidationException("UnionId 不能为空");
        }
        this.value = value;
    }

    public static UnionId of(String value){
        return new UnionId(value);
    }
}
