package world.snowcrystal.template.domain.post.dto.command;


import lombok.Data;

import java.util.List;

@Data
public class PostEditResponse {

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

}
