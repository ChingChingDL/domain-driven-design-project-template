package world.snowcrystal.template.domain.user.type;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class Username {

    @NotBlank(message = "Username must not be blank")
    @Max(value = 20, message = "Username must not be longer than 20 characters")
    @Min(value = 5, message = "Username must not be shorter than 5 characters")
//    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username must contain only alphanumeric characters and underscores")
    String value;


    public Username(String value) {
        //Username must not be blank
        //Username must not be longer than 20 characters
        //Username must not be shorter than 5 characters

        if (value == null || value.isBlank()) {
            throw new ValidationException("Username must not be blank");
        } else if (value.length() > 20 || value.length() < 5) {
            throw new ValidationException("用户名长度必须在 5 到 20 个字符之间");
        }// TODO 用户名只能包含汉字、字母、数字，下划线，破折号
//        else {
//            throw new ValidationException("用户名只能包含字母、数字和下划线");
//        }
        this.value = value;
    }
    public static Username of(String value) {
        return new Username(value);
    }


}
