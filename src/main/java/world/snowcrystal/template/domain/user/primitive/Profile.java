package world.snowcrystal.template.domain.user.primitive;

import jakarta.validation.ValidationException;
import lombok.Value;

@Value
public class Profile {
    String value;

    public Profile(String value) {
        // 500 个字符以内
        if (value.length() > 500) {
            throw new ValidationException("简介太长了`(*>﹏<*)′");
        }
        this.value = value;
    }

    public static Profile of(String value) {
        return new Profile(value);
    }
}
