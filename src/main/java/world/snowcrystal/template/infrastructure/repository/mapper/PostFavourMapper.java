package world.snowcrystal.template.infrastructure.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;
import world.snowcrystal.template.infrastructure.repository.po.PostFavourPO;

/**
 * 文章收藏数据库操作
 *
 *
 *
 */
public interface PostFavourMapper extends BaseMapper<PostFavourPO> {

    /**
     * 分页查询收藏文章列表
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<PostPO> listFavourPostByPage(IPage<PostPO> page, @Param(Constants.WRAPPER) Wrapper<PostPO> queryWrapper,
                                      long favourUserId);

}




