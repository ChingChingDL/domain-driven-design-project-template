package world.snowcrystal.template.mapper;

import jakarta.annotation.Resource;
import world.snowcrystal.template.infrastructure.repository.mapper.PostMapper;
import world.snowcrystal.template.infrastructure.repository.po.PostPO;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 文章数据库操作测试
 *
 *
 *
 */
@SpringBootTest
class PostPOMapperTest {

    @Resource
    private PostMapper postMapper;

    @Test
    void listPostWithDelete() {
//        List<PostPO> postPOList = postMapper.listPostWithDelete(new Date());
//        Assertions.assertNotNull(postPOList);
    }
}
