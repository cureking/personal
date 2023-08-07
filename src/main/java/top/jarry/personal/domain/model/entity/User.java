package top.jarry.personal.domain.model.entity;

import com.google.common.base.Joiner;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import top.jarry.personal.infrastructure.common.ServerResponse;
import top.jarry.personal.infrastructure.enums.UserRoleEnum;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 用户领域模型
 * @Date 2023/8/6 17:07
 * @Author king
 */
@Getter
public class User {

    /**
     * 用户编码
     * 具有唯一性
     */
    private String code;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 5, max = 20, message = "用户名长度必须在5到20个字符之间")
    private String name;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 32, message = "新密码长度不能少于6个字符，不能多于32个字符")
    private String password;

    /**
     * 邮箱
     */
    @NotBlank
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 头像缩略图
     */
    private String avatarThumbnail;

    /**
     *
     */
    @NotBlank(message = "用户角色不能为空")
    private UserRoleEnum role;

//    public User(String name, String password, String email, String avatar, String avatarThumbnail) {
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.avatar = avatar;
//        this.avatarThumbnail = avatarThumbnail;
//    }

    /**
     * 注册
     */
    public ServerResponse register(String name, String password, String email, String avatar, String avatarThumbnail) {
        // 默认用户缩略图，应该由应用层进行处理。而code则由基础设施层进行处理（如果需要定制化，也可以迁移到应用层处理）
//        // 默认缩略图
//        this.avatar = "https://avatars.githubusercontent.com/u/10251037?v=4";
//        this.avatarThumbnail = "https://avatars.githubusercontent.com/u/10251037?v=4";

        // 使用Hibernate Validator进行验证
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(this);
        if (violations.isEmpty()) {
            return ServerResponse.createBySuccess();
        }

        String errMsgs = Optional.of(violations).stream()
                .flatMap(Collection::stream)
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        return ServerResponse.createByErrorMessage(errMsgs);
    }

    /**
     * 验证密码是否相等
     * @param inputPassword
     * @return
     */
    public boolean verifyPassword(String inputPassword) {
        return Objects.equals(this.password, inputPassword);
    }

    /**
     * 修改密码
     * @param originPassword
     * @param newPassword
     * @return
     */
    public ServerResponse<User> changePassword(String originPassword, String newPassword) {
        if (!verifyPassword(originPassword)) {
            return ServerResponse.createByErrorMessage("原密码不正确");
        }
        if (Objects.equals(this.password, newPassword)) {
            return ServerResponse.createByErrorMessage("新密码不能与原密码相同");
        }
        this.password = newPassword;
        return ServerResponse.createBySuccess(this);
    }

    public ServerResponse<User> updateProfile(String name, String email, String phone) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
            String errMsgs = Optional.of(violations).stream()
                    .flatMap(Collection::stream)
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));

            return ServerResponse.createBySuccess(this);
        }

        this.name = name;
        this.email = email;
        this.phone = phone;

        return ServerResponse.createBySuccess(this);
    }

}
