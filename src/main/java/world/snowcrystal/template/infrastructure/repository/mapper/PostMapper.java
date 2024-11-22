package world.snowcrystal.template.infrastructure.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;

import java.util.Date;
import java.util.List;

/**
 * 文章数据库操作
 *
 *
 *
 */
public interface PostMapper extends BaseMapper<PostPO> {

    /**
     * 查询文章列表（包括已被删除的数据）
     */
    List<PostPO> listPostWithDelete(Date minUpdateTime);

}




