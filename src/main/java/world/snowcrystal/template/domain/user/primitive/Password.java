package world.snowcrystal.template.domain.user.primitive;

import jakarta.validation.ValidationException;
import lombok.Value;

@Value
public class Password {
    String value;

    public Password(String value) {
        // 密码不能为空 / 空字符串
        if (value == null || value.isBlank()) {
            throw new ValidationException("密码不能为空");
        }
        // 密码至少为 8 个字符
        if (value.length() < 8) {
            throw new ValidationException("密码至少为 8 个字符");
        }
        this.value = value;
    }

    public static Password of(String value) {
        return new Password(value);
    }
}
