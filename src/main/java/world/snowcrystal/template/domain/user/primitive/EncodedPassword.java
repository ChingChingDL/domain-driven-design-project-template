package world.snowcrystal.template.domain.user.primitive;

import world.snowcrystal.template.domain.register.component.PasswordEncoder;
import jakarta.validation.ValidationException;
import lombok.Value;


/**
 * 加密后的密码
 * @see PasswordEncoder
 */
@Value
public class EncodedPassword {

    String value;

    public EncodedPassword( String value) {
        // empty and blank check
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("MpOpenId is empty");
        }

        this.value = value;
    }

    }
