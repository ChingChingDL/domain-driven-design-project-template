package world.snowcrystal.template.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import world.snowcrystal.template.domain.common.dto.Page;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.enums.SortOrderEnum;
import world.snowcrystal.template.domain.common.exception.BusinessException;
import world.snowcrystal.template.domain.common.type.Id;
import world.snowcrystal.template.domain.user.dto.query.UserQuery;
import world.snowcrystal.template.domain.user.entity.User;
import world.snowcrystal.template.domain.user.repository.UserRepository;
import world.snowcrystal.template.domain.user.type.Account;
import world.snowcrystal.template.domain.user.type.UnionId;
import world.snowcrystal.template.domain.user.type.Username;
import world.snowcrystal.template.infrastructure.converter.EntityConverter;
import world.snowcrystal.template.infrastructure.repository.mapper.UserMapper;
import world.snowcrystal.template.infrastructure.repository.po.UserPO;
import world.snowcrystal.template.infrastructure.utils.SqlUtils;

import java.util.stream.Collectors;

@Repository("userRepository")
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserPO>
        implements IService<UserPO>, UserRepository {


    @Resource
    private UserMapper userMapper;

    @Resource
    private EntityConverter entityConverter;



    @Override
    public User load(Id id) {
        UserPO user = userMapper.selectById(id.getValue());
        if (user == null) {
            throw new BusinessException(ApplicationResponseStatusCode.NOT_FOUND_ERROR, "User not found");
        }
        return entityConverter.toEntity(user);
    }

    @Override
    public User load(Account account) {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account.getValue())
                .eq("deleted", 0);
        UserPO user = userMapper.selectOne(queryWrapper);
        return entityConverter.toEntity(user);
    }

    @Override
    public User load(Username username) {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username.getValue())
                .eq("deleted", 0);
        UserPO user = userMapper.selectOne(queryWrapper);
        return entityConverter.toEntity(user);
    }

    @Override
    public User load(UnionId unionId) {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unionId", unionId.getValue())
                .eq("deleted", 0);
        return entityConverter.toEntity(userMapper.selectOne(queryWrapper));
    }

    @Override
    public void save(User user) {
        userMapper.insert(entityConverter.toPO(user));
    }


    @Override
    public void delete(Id id) {
        userMapper.deleteById(id.getValue());
    }

    @Override
    public void delete(Account account) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account.getValue());
        userMapper.delete(wrapper);
    }

    @Override
    public void delete(Username username) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username.getValue());
        userMapper.delete(wrapper);
    }

    @Override
    public void delete(User user) {
        UserPO po = entityConverter.toPO(user);
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        wrapper.eq("id", po.getId());
        userMapper.delete(wrapper);
    }

    @Override
    public Page<User> findAll(long current, long size) {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", 0);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserPO> page
                = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();

        page.setCurrent(current)
                .setSize(size);

        page = userMapper.selectPage(page, queryWrapper);

        return Page.<User>builder()
                .current(current)
                .size(size)
                .total(page.getTotal())
                .records(page.getRecords().stream().map(entityConverter::toEntity).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Page<UserPO> findAll(UserQuery query) {
        QueryWrapper<UserPO> queryWrapper = getQueryWrapper(query);
        queryWrapper.eq("deleted", 0);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<UserPO> page
                = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();

        page.setCurrent(query.getCurrent())
                .setSize(query.getSize());

        page = userMapper.selectPage(page, queryWrapper);

        return Page.<UserPO>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(page.getRecords())
                .build();

    }

    @Override
    public boolean checkExists(Username username) {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username.getValue())
                .select("id")
                .eq("deleted", 0);
        return userMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public boolean checkExists(Account account) {
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account.getValue())
                .select("id")
                .eq("deleted", 0);
        return userMapper.selectOne(queryWrapper) != null;
    }

    private QueryWrapper<UserPO> getQueryWrapper(UserQuery userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ApplicationResponseStatusCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String unionId = userQueryRequest.getUnionId();
        String mpOpenId = userQueryRequest.getMpOpenId();
        String userName = userQueryRequest.getUsername();
        String userProfile = userQueryRequest.getProfile();
        String userRole = userQueryRequest.getRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(unionId), "unionId", unionId);
        queryWrapper.eq(StringUtils.isNotBlank(mpOpenId), "mpOpenId", mpOpenId);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(SortOrderEnum.ASCENDING.getValue()),
                sortField);
        return queryWrapper;
    }


}
