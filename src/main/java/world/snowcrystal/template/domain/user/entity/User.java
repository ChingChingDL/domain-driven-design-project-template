package world.snowcrystal.template.domain.user.entity;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.util.DigestUtils;
import world.snowcrystal.template.domain.common.type.*;
import world.snowcrystal.template.domain.user.enums.UserRoleEnum;
import world.snowcrystal.template.domain.user.type.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Getter
@Builder
public class User implements Serializable {
    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "TechnologyMillennium";

    /**
     * id
     */
    @Nonnull
    private Id id;

    /**
     * 用户账号
     */
    @Nonnull
    private Account account;

    /**
     * 用户密码
     */
    @Nonnull
    private Password password;

    /**
     * 开放平台id
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    private UnionId unionId;

    /**
     * 公众号openId
     */
    @Getter
    @Setter
    @Accessors(chain = true)
    private MpOpenId mpOpenId;

    /**
     * 用户昵称
     */
    @Nonnull
    private Username username;

    /**
     * 用户头像
     */
    @Nonnull
    private Avatar avatar;

    /**
     * 用户简介
     */
    @Nonnull
    private Profile profile;

    /**
     * 用户角色：user/admin/ban
     */
    @Nonnull
    private Role role;

    /**
     * 创建时间
     */
    @Nonnull
    @Getter
    @Setter
    @Accessors(chain = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @Nonnull
    @Getter
    @Setter
    @Accessors(chain = true)
    private Date updateTime;


    /**
     * 是否删除
     */
    @Nonnull
    private Integer deleted;

    public void delete() {
        this.deleted = 1;
    }

    public User changePassword(Password password) {
        this.password = encode(password);
        return this;
    }

    public User changeUsername(Username username) {
        this.username = username;
        return this;
    }

    public User changeUsername(String username) {
        return changeUsername(Username.of(username));
    }

    public User changeAvatar(Avatar avatar) {
        this.avatar = avatar;
        return this;
    }

    public User changeAvatar(String avatar) {
        return changeAvatar(Avatar.of(avatar));
    }

    public User changeProfile(Profile userProfile) {
        this.profile = userProfile;
        return this;
    }

    public User changeProfile(String userProfile) {
        return changeProfile(Profile.of(userProfile));
    }

    public User changeUserRole(UserRoleEnum userRole) {
        this.role = Role.of(userRole);
        return this;
    }
    public User changeUserRole(String userRole) {
        return changeUserRole(UserRoleEnum.getEnumByValue(userRole));
    }

    public boolean checkIsAdmin() {
        return UserRoleEnum.ADMIN.equals(this.role.getValue());
    }

    public boolean activate() {
        return this.deleted == 0;
    }

    public boolean isBanned() {
        return UserRoleEnum.BAN.equals(this.role.getValue());
    }

    public void bindWx(UnionId unionId, MpOpenId mpOpenId) {
        this.unionId = unionId;
        this.mpOpenId = mpOpenId;
    }

    /**
     * @param password 未被加密的密码哦
     * @return true 如果密码匹配
     */
    public boolean matches(Password password) {
        return this.password.equals(encode(password));
    }

    public User setPassword(Password password) {
        this.password = encode(password);
        return this;
    }


    public static Password encode(Password password) {
        return new Password(DigestUtils.md5DigestAsHex((SALT + password).getBytes()));
    }


//    public void setDeleted(@Nonnull Integer deleted) {
//        this.deleted = deleted;
//    }
//
//    public void setUpdateTime(@Nonnull Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public void setCreateTime(@Nonnull Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public void setRole(@Nonnull String role) {
//        this.role = Role.of(role);
//    }
//
//    public void setProfile(@Nonnull String profile) {
//        this.profile = Profile.of(profile);
//    }
//
//    public void setAvatar(@Nonnull String avatar) {
//        this.avatar = Avatar.of(avatar);
//    }
//
//    public void setUsername(@Nonnull String username) {
//        this.username = Username.of(username);
//    }
//
//
//    public void setAccount(@Nonnull String account) {
//        this.account = Account.of(account);
//    }
//
//    public void setId(@Nonnull Long id) {
//        this.id = Id.of(id);
//    }

    @Serial
    private static final long serialVersionUID = 1L;
}