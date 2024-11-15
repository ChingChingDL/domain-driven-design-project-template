package world.snowcrystal.template.domain.user.type;

import jakarta.validation.ValidationException;
import lombok.Value;

@Value
public class Avatar {
    String value;

    public Avatar(String value) {
        // non-null / non-blank check
        if (value == null || value.isBlank()) {
            throw new ValidationException("Avatar value cannot be null or blank");
        }
        this.value = value;
    }

    public static Avatar of(String value) {
        return new Avatar(value);
    }
}
