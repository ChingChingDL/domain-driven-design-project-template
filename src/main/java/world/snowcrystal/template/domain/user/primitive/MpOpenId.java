package world.snowcrystal.template.domain.user.primitive;

import jakarta.validation.ValidationException;
import lombok.Value;

@Value
public class MpOpenId {
    String value;

    public MpOpenId(String value) {
        // empty and blank check
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("MpOpenId is empty");
        }
        this.value = value;
    }
    public static MpOpenId of(String value){
        return new MpOpenId(value);
    }
}
