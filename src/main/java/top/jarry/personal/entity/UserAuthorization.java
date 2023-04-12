package top.jarry.personal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import top.jarry.personal.common.PermissionRoleEnum;

/**
 * @Description 用户权限
 * @Date 2023/4/12 22:51
 * @Author king
 */
@Entity
@Data
public class UserAuthorization {

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
     * 用户权限角色名
     * @see PermissionRoleEnum#name()
     */
    private String roleName;
}
