package world.snowcrystal.template.domain.file.command;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 文件上传请求
 *
 *
 *
 */
@Data
public class UploadFileCommand implements Serializable {

    /**
     * 业务
     */
    private String biz;

    @Serial
    private static final long serialVersionUID = 1L;
}
