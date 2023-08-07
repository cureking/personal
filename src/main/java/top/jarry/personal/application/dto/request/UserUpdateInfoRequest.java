package top.jarry.personal.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import top.jarry.personal.infrastructure.enums.UserRoleEnum;

/**
 * @Description 用户更新信息请求
 * @Date 2023/8/6 19:19
 * @Author king
 */
@Data
public class UserUpdateInfoRequest {
    /**
     * 用户编码
     * 具有唯一性
     */
    private String code;

    /**
     * 用户名
     */
    @Size(min = 5, max = 20, message = "用户名长度必须在5到20个字符之间")
    private String name;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号码
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号码格式不正确")
    private String phone;

}
