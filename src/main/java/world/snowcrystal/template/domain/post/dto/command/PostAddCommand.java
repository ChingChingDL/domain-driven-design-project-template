package world.snowcrystal.template.domain.post.dto.command;

import lombok.Data;
import world.snowcrystal.template.domain.common.command.Command;

import java.io.Serial;
import java.util.List;

/**
 * 创建请求
 *
 *
 *
 */
@Data
public class PostAddCommand implements Command {

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
