package world.snowcrystal.template.domain.favorite.dto.command;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
public class PostFavouriteResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long totalFavours;
}
