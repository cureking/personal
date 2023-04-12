package top.jarry.personal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 用户信息
 * @Date 2023/3/2 00:57
 * @Author king
 */
@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户唯一标识
     * @see User#getId()
     */
    private long userId;

    /**
     * 用户编码
     * @see User#getCode()
     */
    private String userCode;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 头像缩略图
     */
    private String avatarThumbnail;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 电子邮箱
     */
    private String emailAddress;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 备注
     */
    private String mark;
}
