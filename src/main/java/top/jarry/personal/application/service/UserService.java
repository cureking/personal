package top.jarry.personal.application.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import top.jarry.personal.application.converters.UserDtoConverter;
import top.jarry.personal.application.dto.request.UserRegisterRequest;
import top.jarry.personal.application.dto.request.UserUpdateInfoRequest;
import top.jarry.personal.application.dto.response.UserInfoResponse;
import top.jarry.personal.domain.model.entity.User;
import top.jarry.personal.domain.service.UserDomainService;
import top.jarry.personal.infrastructure.common.ServerResponse;

/**
 * @Description TODO
 * @Date 2023/8/6 22:18
 * @Author king
 */
@Service()
public class UserService {
    @Resource
    private UserDomainService userDomainService;

    /**
     * 获取用户信息
     * @param code
     * @return
     */
    public ServerResponse<UserInfoResponse> getUserInfo(@Nonnull String code) {
        ServerResponse<User> userDomainProfileResponse = userDomainService.getProfile(code);
        if (!userDomainProfileResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage(userDomainProfileResponse.getMsg());
        }
        User user = userDomainProfileResponse.getData();
        UserInfoResponse userInfoResponse = UserDtoConverter.convertToUserInfoResponse(user);

        return ServerResponse.createBySuccess(userInfoResponse);
    }

    /**
     * 注册
     * @param request
     * @return
     */
    public ServerResponse<UserInfoResponse> register(@Valid UserRegisterRequest request) {

        ServerResponse<User> userDomainProfileResponse = userDomainService.register(request);
        if (!userDomainProfileResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage(userDomainProfileResponse.getMsg());
        }
        User user = userDomainProfileResponse.getData();
        UserInfoResponse userInfoResponse = UserDtoConverter.convertToUserInfoResponse(user);

        return ServerResponse.createBySuccess(userInfoResponse);
    }

    public ServerResponse<UserInfoResponse> login(@Valid @NotBlank String name, @NotBlank String password) {

        ServerResponse<User> userDomainProfileResponse = userDomainService.login(name, password);
        if (!userDomainProfileResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage(userDomainProfileResponse.getMsg());
        }
        User user = userDomainProfileResponse.getData();
        UserInfoResponse userInfoResponse = UserDtoConverter.convertToUserInfoResponse(user);

        return ServerResponse.createBySuccess(userInfoResponse);
    }

    /**
     * 更新用户信息
     * @param request
     * @return
     */
    public ServerResponse<UserInfoResponse> updateUserInfo(@Valid UserUpdateInfoRequest request) {

        ServerResponse<User> userDomainProfileResponse = userDomainService.updateProfile(request);
        if (!userDomainProfileResponse.isSuccess()) {
            return ServerResponse.createByErrorMessage(userDomainProfileResponse.getMsg());
        }
        User user = userDomainProfileResponse.getData();
        UserInfoResponse userInfoResponse = UserDtoConverter.convertToUserInfoResponse(user);

        return ServerResponse.createBySuccess(userInfoResponse);
    }

    /**
     * 注销
     * @return
     */
    public ServerResponse logout() {
        return ServerResponse.createBySuccess();
    }
}
