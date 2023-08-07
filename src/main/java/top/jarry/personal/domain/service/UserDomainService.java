package top.jarry.personal.domain.service;

import jakarta.annotation.Resource;
import jakarta.validation.*;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import top.jarry.personal.application.dto.request.UserChangePasswordRequest;
import top.jarry.personal.application.dto.request.UserRegisterRequest;
import top.jarry.personal.application.dto.request.UserUpdateInfoRequest;
import top.jarry.personal.domain.model.entity.User;
import top.jarry.personal.domain.repository.UserRepository;
import top.jarry.personal.infrastructure.common.ServerResponse;

/**
 * @Description 用户领域服务
 * @Date 2023/8/6 18:06
 * @Author king
 */
@Service
public class UserDomainService {
    private static final String DEFAULT_ACATAR = "https://avatars.githubusercontent.com/u/10251037?v=4";
    private static final String DEFAULT_ACATAR_THUMBNAIL = "https://avatars.githubusercontent.com/u/10251037?v=4";

    @Resource
    private UserRepository userRepository;

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    public ServerResponse<User> register(@Valid UserRegisterRequest userRegisterRequest) {
        User existedUser = userRepository.findByUsername(userRegisterRequest.getName());
        if (existedUser != null) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }

        User user = new User();
        String encryptedPassword = encryptPassword(userRegisterRequest.getPassword());
        ServerResponse serverResponse = user.register(userRegisterRequest.getName(), encryptedPassword, userRegisterRequest.getEmail(),
                DEFAULT_ACATAR, DEFAULT_ACATAR_THUMBNAIL);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }

        userRepository.save(user);
        return ServerResponse.createBySuccess(user);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public ServerResponse<User> login(@Valid @NotBlank String username, @NotBlank String password) {
        User user = userRepository.findByUsername(username);

        String encryptedPassword = encryptPassword(password);
        if (user != null && user.verifyPassword(encryptedPassword)) {
            return ServerResponse.createBySuccess(user);
        }

        return ServerResponse.createByErrorMessage("用户名或密码错误");
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    public ServerResponse changePassword(@Valid UserChangePasswordRequest request) {
        User exitedUser = userRepository.findByUserCode(request.getCode());
        if (exitedUser == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String encryptedPassword = encryptPassword(request.getNewPassword());
        ServerResponse<User> serverResponse = exitedUser.changePassword(request.getOriginPassword(), encryptedPassword);

        return serverResponse;
    }

    // 找回密码

    /**
     * 更新个人信息
     * @param request
     * @return
     */
    public ServerResponse<User> updateProfile(UserUpdateInfoRequest request) {
        User user = userRepository.findByUserCode(request.getCode());
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        if (StringUtils.isNotBlank(request.getName())) {
            User namedUser = userRepository.findByUsername(request.getName());
            if (namedUser != null) {
                return ServerResponse.createByErrorMessage("用户名已存在");
            }
        }

        ServerResponse<User> serverResponse = user.updateProfile(request.getName(), request.getEmail(), request.getPhone());
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }

        userRepository.save(user);
        return ServerResponse.createBySuccess(user);
    }

    /**
     * 查询个人信息
     * @param code
     * @return
     */
    public ServerResponse<User> getProfile(String code) {
        User user = userRepository.findByUserCode(code);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        return ServerResponse.createBySuccess(user);
    }

    /**
     * 加密密码
     * @param password
     * @return
     */
    private String encryptPassword(String password) {
        // 在这里使用BCrypt进行密码加密，这里只是示例，具体实现需要使用安全的加密算法
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
