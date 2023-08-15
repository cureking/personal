package top.jarry.personal.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import top.jarry.personal.infrastructure.common.ServerResponse;
import top.jarry.personal.infrastructure.enums.UserRoleEnum;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 用户领域模型
 * @Date 2023/8/6 17:07
 * @Author king
 */
@Getter
@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 64, nullable = false, unique = true)
    private long id;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime gmtCreate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime gmtModified;

    /**
     * 用户编码
     * 具有唯一性
     */
    @Column(length = 64, unique = true, nullable = false)
    private String code;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 5, max = 20, message = "用户名长度必须在5到20个字符之间")
    @Column(length = 32, unique = true, nullable = false)
    private String name;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Column(length = 64, nullable = false)
    private String password;

    /**
     * 邮箱
     */
    @NotBlank
    @Email(message = "邮箱格式不正确")
    @Column(length = 32)
    private String email;

    /**
     * 手机号
     */
    @Column(length = 16)
    private String phone;

    /**
     * 头像
     */
    @Column(length = 128)
    private String avatar;

    /**
     * 用户角色
     * 默认存储枚举名称
     */
    @Column(length = 32)
    private UserRoleEnum role;

    @PrePersist
    public void prePersist() {
        gmtCreate = LocalDateTime.now();
        gmtModified = gmtCreate;
        this.code = UUID.randomUUID().toString();
    }

    /**
     * 注册
     */
    public ServerResponse register(String name, String password, String email, String avatar) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.role = UserRoleEnum.ROLE_REGISTRANT;

        // 使用Hibernate Validator进行验证
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(this);
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
     *
     * @param inputPassword
     * @return
     */
    public boolean verifyPassword(String inputPassword) {
        return Objects.equals(this.password, inputPassword);
    }

    /**
     * 修改密码
     *
     * @param originPassword
     * @param newPassword
     * @return
     */
    public ServerResponse<UserEntity> changePassword(String originPassword, String newPassword) {
        if (!verifyPassword(originPassword)) {
            return ServerResponse.createByErrorMessage("原密码不正确");
        }
        if (Objects.equals(this.password, newPassword)) {
            return ServerResponse.createByErrorMessage("新密码不能与原密码相同");
        }
        this.password = newPassword;
        return ServerResponse.createBySuccess(this);
    }

    public ServerResponse<UserEntity> updateProfile(String name, String email, String phone) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(this);
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
