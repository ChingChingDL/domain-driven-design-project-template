package world.snowcrystal.template.domain.user;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.entity.UserFactory;
import world.snowcrystal.template.domain.user.primitive.Account;
import world.snowcrystal.template.domain.user.primitive.Password;
import world.snowcrystal.template.domain.user.repository.UserRepository;

@SpringBootTest
public class UserCURDTest {

    @Resource
    private UserFactory userFactory;

    @Resource
    private UserRepository userRepository;

    private final Account account = Account.of("hifddddsd");
    private final Password password = Password.of("12345678");

    @Test
    @DisplayName("用户创建测试")
    public void createTest() {
        User user = userFactory.create(account, password);
        Assertions.assertNotNull(user);
        Assertions.assertNotEquals(user.getId().getValue(), 0L);
        userRepository.save(user);
    }


    @Test
    @DisplayName("用户重复创建测试")
    public void duplicationTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            userFactory.create(account, password);
        });
    }

    // 用户删除测试
    @Test
    @DisplayName("用户删除测试")
    public void deleteTest() {
        User user = userRepository.load(account);
        Assertions.assertNotNull(user);
        Assertions.assertNotEquals(user.getId().getValue(), 0L);
        userRepository.remove(user);
    }




}
