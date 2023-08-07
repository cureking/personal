package top.jarry.personal.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @description:
 * @author: 天数
 * @since: 2022/10/14 11:30
 */
@Entity
@Data
public class User {

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户编码
     */
    @Column(unique = true)
    private String code;

    /**
     * 用户账号
     * 电子邮箱/手机号码
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    @OneToOne
    @JoinColumn(name = "userInfoId", referencedColumnName = "id")
    private UserInfo userInfo;

    @OneToOne
    @JoinColumn(name = "userAuthorizationId", referencedColumnName = "id")
    private UserAuthorization userAuthorization;
}
