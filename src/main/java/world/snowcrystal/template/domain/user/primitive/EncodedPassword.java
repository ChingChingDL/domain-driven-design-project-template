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
    transient PasswordEncoder passwordEncoder;
    String value;

    public EncodedPassword(PasswordEncoder passwordEncoder, String value) {
        // empty and blank check
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException("MpOpenId is empty");
        }
        this.passwordEncoder = passwordEncoder;
        this.value = value;
    }

    public boolean matches(Password rawPassword) {
        return passwordEncoder.encode(rawPassword).equals(this);
    }
}
