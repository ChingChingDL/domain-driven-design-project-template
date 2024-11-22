package world.snowcrystal.template.domain.post.primitive;

import lombok.Value;

@Value
public class Tag {
    String value;

    public Tag(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Tag value cannot be null or empty");
        } else if (value.length() > 10)
            throw new IllegalArgumentException("Tag value cannot be more than 10 characters");
        this.value = value;
    }
    public static Tag of(String value) {
        return new Tag(value);
    }

}
