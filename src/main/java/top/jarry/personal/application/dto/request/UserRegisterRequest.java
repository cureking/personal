package top.jarry.personal.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Description 用户注册请求
 * @Date 2023/8/6 18:24
 * @Author king
 */
@Data
public class UserRegisterRequest {

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
//http://localhost:2333/user/register?name=jarry&password=123456&email=7777777@qq.com
}
