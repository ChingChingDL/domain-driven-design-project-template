package world.snowcrystal.template.domain.post.primitive;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
public class Title {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$",message = "标题只能包含字母、数字和中文")
    @Max(value = 120,message = "标题最长为 120 个字符")
    String value;

    public static Title of(String value) {
        return new Title(value);
    }
}
