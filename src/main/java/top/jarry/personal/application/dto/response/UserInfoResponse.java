package top.jarry.personal.application.dto.response;

import lombok.Data;

/**
 * @Description TODO
 * @Date 2023/8/6 22:24
 * @Author king
 */
@Data
public class UserInfoResponse {

    private String code;
    private String name;
    private String email;
    private String phone;
    private String avatar;
    private String avatarThumbnail;
}
