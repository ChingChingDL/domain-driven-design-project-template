package world.snowcrystal.template.domain.post.dto.command;

import jakarta.validation.constraints.Min;
import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;
import java.util.List;

/**
 * 更新请求
 */
@Data
public class PostUpdateCommand implements Command {

    /**
     * id
     */
    @Min(0)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;
    @Serial
    private static final long serialVersionUID = 1L;
}
