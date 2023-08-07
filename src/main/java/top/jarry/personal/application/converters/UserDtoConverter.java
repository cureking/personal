package top.jarry.personal.application.converters;

import org.springframework.beans.BeanUtils;
import top.jarry.personal.application.dto.response.UserInfoResponse;
import top.jarry.personal.domain.model.entity.User;

/**
 * @Description TODO
 * @Date 2023/8/6 22:30
 * @Author king
 */
public class UserDtoConverter {

    public static UserInfoResponse convertToUserInfoResponse(User user) {
        if (user == null) {
            return null;
        }
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(user, userInfoResponse);

        return userInfoResponse;
    }
}
