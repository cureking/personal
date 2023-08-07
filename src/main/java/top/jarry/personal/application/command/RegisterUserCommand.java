package top.jarry.personal.application.command;

import lombok.Data;
import top.jarry.personal.domain.model.entity.User;

/**
 * @Description 用户注册命令
 * @Date 2023/8/8 00:51
 * @Author king
 */
@Data
public class RegisterUserCommand {

    private static final String DEFAULT_ACATAR = "https://avatars.githubusercontent.com/u/10251037?v=4";
    private static final String DEFAULT_ACATAR_THUMBNAIL = "https://avatars.githubusercontent.com/u/10251037?v=4";

    private String username;

    private String password;

    private String email;

    public static User of(RegisterUserCommand command) {
        User user = new User(command.getUsername(), command.getPassword(), command.getEmail(),
                DEFAULT_ACATAR, DEFAULT_ACATAR_THUMBNAIL);

        return user;
    }
}
