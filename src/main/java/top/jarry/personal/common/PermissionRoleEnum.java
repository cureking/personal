package top.jarry.personal.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 权限角色枚举
 * 暂定权限向下兼容
 * @Date 2023/3/2 00:43
 * @Author king
 */
@AllArgsConstructor
@Getter
public enum PermissionRoleEnum {
    ROLE_ADMIN(20, "管理员"),
    ROLE_CREATOR(30, "创作者"),
    ROLE_FULL_REGISTRANT(40, "完整注册者"),
    ROLE_INCOMPLETE_REGISTRANT(45, "不完整注册者"),
    ROLE_VISITOR(50, "访客"),
    ;

    private int code;
    private String desc;
}
