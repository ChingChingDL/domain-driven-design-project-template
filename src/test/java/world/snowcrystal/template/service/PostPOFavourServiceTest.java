package world.snowcrystal.template.service;

import world.snowcrystal.template.infrastructure.repository.po.UserPO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 文章收藏服务测试
 *
 *
 *
 */
@SpringBootTest
class PostPOFavourServiceTest {

    @Resource
    private PostFavourService postFavourService;

    private static final UserPO loginUser = new UserPO();

    @BeforeAll
    static void setUp() {
        loginUser.setId(1L);
    }

    @Test
    void doPostFavour() {
//        int i = postFavourService.doPostFavour(1L, loginUser);
//        Assertions.assertTrue(i >= 0);
    }

    @Test
    void listFavourPostByPage() {
//        QueryWrapper<PostPO> postQueryWrapper = new QueryWrapper<>();
//        postQueryWrapper.eq("id", 1L);
//        postFavourService.listFavourPostByPage(Page.of(0, 1), postQueryWrapper, loginUser.getId());
    }
}
