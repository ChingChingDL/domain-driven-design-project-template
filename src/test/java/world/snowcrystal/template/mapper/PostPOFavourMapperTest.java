package world.snowcrystal.template.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import world.snowcrystal.template.infrastructure.repository.mapper.PostFavourMapper;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 文章收藏数据库操作测试
 *
 *
 *
 */
@SpringBootTest
class PostPOFavourMapperTest {

    @Resource
    private PostFavourMapper postFavourMapper;

    @Test
    void listUserFavourPostByPage() {

    }
}
