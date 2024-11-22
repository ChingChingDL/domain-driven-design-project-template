package world.snowcrystal.template.domain.common.dto;


import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

@Builder
public class Page<T> implements Serializable {

    private List<T> records;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页显示条数
     */
    private Long size;

    /**
     * 总页数
     */
    private Long total;

    public static <T> Page<T> empty(Class<T> clazz) {
        return Page.<T>builder().build();
    }
    public Stream<T> stream() {
        return records.stream();
    }
    @Serial
    private static final long serialVersionUID = 1L;
}
