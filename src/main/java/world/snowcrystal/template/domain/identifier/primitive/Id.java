package world.snowcrystal.template.domain.identifier.primitive;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Min;
import lombok.Value;

@Value
public class Id {
    @Min(0)
    Long value;

    public Id(Long value) {
        if (value == null) {
            throw new ValidationException("id 不能为空");
        }
        if (value < 0) {
            throw new ValidationException("id 不能为负数");
        }
        this.value = value;
    }

    public static Id of(Long value) {
        return new Id(value);
    }
}
