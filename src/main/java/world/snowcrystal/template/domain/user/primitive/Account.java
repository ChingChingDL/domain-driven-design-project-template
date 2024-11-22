package world.snowcrystal.template.domain.user.primitive;

import jakarta.validation.ValidationException;
import lombok.Value;

@Value
public class Account {

    String value;

    public Account(String value) {
        if (value == null) {
            throw new ValidationException("用户账号不能为空");
        } else if (value.length() < 4 || value.length() > 24) {
            throw new ValidationException("用户账号长度必须为 4-24 个字符哦");
        } else if (!value.matches("^[a-zA-Z0-9_]+$")) {
            throw new ValidationException("用户账号只能包含字母数字和下划线");
        }
        this.value = value;
    }
    public static Account of(String value){
        return new Account(value);
    }

}
