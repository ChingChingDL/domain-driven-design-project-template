package world.snowcrystal.template.domain.post.type;

import lombok.Value;

@Value
public class Content {
    String value;

    public Content(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Content cannot be blank");
        } else if (value.length() > 2000000) {
            throw new IllegalArgumentException("Content cannot be longer than 2MB");
        }
        this.value = value;
    }


    public Content of(String value){
        return new Content(value);
    }
}
