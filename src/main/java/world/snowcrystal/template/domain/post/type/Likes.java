package world.snowcrystal.template.domain.post.type;

import lombok.Value;

/**
 * 点赞数
 */

@Value
public class Likes {


    Integer value;

    public Likes(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("点赞数不能为空");
        } else if (value < 0) {
            throw new IllegalArgumentException("点赞数不能小于0");
        }
        this.value = value;
    }

    public static Likes of(Integer value) {
        return new Likes(value);
    }
}
