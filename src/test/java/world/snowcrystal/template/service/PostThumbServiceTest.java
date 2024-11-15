package world.snowcrystal.template.service;

import world.snowcrystal.template.application.service.PostThumbService;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 文章点赞服务测试
 *
 *
 *
 */
@SpringBootTest
class PostThumbServiceTest {

    @Resource
    private PostThumbService postThumbService;

    private static final UserPO loginUser = new UserPO();

    @BeforeAll
    static void setUp() {
        loginUser.setId(1L);
    }

    @Test
    void doPostThumb() {
        int i = postThumbService.doPostThumb(1L, loginUser);
        Assertions.assertTrue(i >= 0);
    }
}
