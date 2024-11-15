package world.snowcrystal.template.domain.user.repository;

import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.common.type.Id;
import world.snowcrystal.template.domain.user.dto.query.UserQuery;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.type.Account;
import world.snowcrystal.template.domain.user.type.UnionId;
import world.snowcrystal.template.domain.user.type.Username;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;

public interface UserRepository {

    /**
     * 根据id查询用户
     * @throws world.snowcrystal.template.domain.common.exception.BusinessException 当用户不存在时抛出
     */
    User load(Id id);

    User load(Account account);

    User load(Username username);

    User load(UnionId unionId);

    void save(User user);

    void delete(Id id);

    void delete(Account account);

    void delete(Username username);

    void delete(User user);

    Page<User> findAll(long current, long size);

    Page<UserPO> findAll(UserQuery query);
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
