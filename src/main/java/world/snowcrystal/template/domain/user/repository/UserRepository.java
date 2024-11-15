package world.snowcrystal.template.domain.user.repository;

import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.identifier.primitive.Id;
import world.snowcrystal.template.domain.user.dto.query.UserQuery;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.primitive.Account;
import world.snowcrystal.template.domain.user.primitive.UnionId;
import world.snowcrystal.template.domain.user.primitive.Username;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;
import  world.snowcrystal.template.domain.common.exception.BusinessException;

public interface UserRepository {

    /**
     * 根据id查询用户
     * @throws BusinessException 当用户不存在时抛出
     */
    User load(Id id);

    User load(Account account);

    User load(Username username);

    User load(UnionId unionId);

    void save(User user);

    void remove(Id id);

    void remove(Account account);

    void remove(Username username);

    void remove(User user);

    Page<User> loadBatch(long current, long size);

    Page<UserPO> loadBatch(UserQuery query);
    /**
     * 检查用户名是否已经存在
     *
     * @param username 用户名
     * @return true 如果该用户名已经存在，否则返回 false
     */
    boolean checkExists(Username username);

    /**
     * 检查账号是否已经存在
     *
     * @param account 账号
     * @return true 如果该账号已经存在，否则返回 false
     */
    boolean checkExists(Account account);

}
