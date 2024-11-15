package world.snowcrystal.template.domain.common.query;

import lombok.Data;
import world.snowcrystal.template.domain.common.enums.SortOrderEnum;

/**
 * 分页请求
 */
@Data
public class PageQuery  implements Query{

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int size = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = SortOrderEnum.ASCENDING.getValue();
}
