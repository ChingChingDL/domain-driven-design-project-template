package world.snowcrystal.template.domain.common.command;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.io.Serial;

/**
 * 删除请求
 */
@Data
public class DeleteCommand implements Command {

    /**
     * id
     */
    @Min(0)
    @Nonnull
    private Long id;

    @Serial
    private static final long serialVersionUID = 1L;
}
