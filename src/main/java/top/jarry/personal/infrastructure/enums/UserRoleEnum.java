package top.jarry.personal.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description TODO
 * @Date 2023/8/6 17:28
 * @Author king
 */
@AllArgsConstructor
@Getter
public enum UserRoleEnum {

    ROLE_ADMIN(2, "管理员"),
    ROLE_REGISTRANT(1, "注册者"),
    ROLE_UNKNOW(-1, "未知");

    private int code;
    private String desc;

    public static UserRoleEnum getByCode(int code) {
        for (UserRoleEnum targetEnum : UserRoleEnum.values()) {
            if (targetEnum.getCode() == code) {
                return targetEnum;
            }
        }
        return ROLE_UNKNOW;
    }
}
