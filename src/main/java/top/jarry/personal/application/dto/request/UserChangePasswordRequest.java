package top.jarry.personal.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Description 用户修改密码请求
 * @Date 2023/8/6 18:59
 * @Author king
 */
@Data
public class UserChangePasswordRequest {

    /**
     * 用户编码
     * 用户修改密码时，一定处于登录状态，所以可以从token中获取
     */
    @NotBlank(message = "用户编码不能为空")
    private String code;

    /**
     * 原密码
     */
    @NotBlank(message = "原密码不能为空")
    private String originPassword;

    /**
     * 密码
     */
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 32, message = "新密码长度不能少于6个字符，不能多于32个字符")
    private String newPassword;
}
