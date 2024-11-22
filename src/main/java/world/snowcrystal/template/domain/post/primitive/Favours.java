package world.snowcrystal.template.domain.post.primitive;

import lombok.Value;

/**
 * 收藏数
 */
@Value
public class Favours {

    Integer value;

    public Favours(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("收藏数不能为空");
        } else if (value < 0) {
            throw new IllegalArgumentException("收藏数不能小于0");
        }
        this.value = value;
    }

    public static Favours of(Integer value) {
        return new Favours(value);
    }
}
