package top.jarry.personal.domain.service;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import top.jarry.personal.application.dto.request.UserChangePasswordRequest;
import top.jarry.personal.application.dto.request.UserRegisterRequest;
import top.jarry.personal.application.dto.request.UserUpdateInfoRequest;
import top.jarry.personal.domain.model.entity.UserEntity;
import top.jarry.personal.infrastructure.common.ServerResponse;
import top.jarry.personal.infrastructure.persistence.UserRepository;

/**
 * @Description 用户领域服务
 * @Date 2023/8/6 18:06
 * @Author king
 */
@Service
public class UserDomainService {
    private static final String DEFAULT_ACATAR = "https://avatars.githubusercontent.com/u/10251037?v=4";

    @Resource
    private UserRepository userRepository;

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    public ServerResponse<UserEntity> register(@Valid UserRegisterRequest userRegisterRequest) {
        UserEntity existedUserEntity = userRepository.findByName(userRegisterRequest.getName());
        if (existedUserEntity != null) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }

        UserEntity userEntity = new UserEntity();
        String encryptedPassword = encryptPassword(userRegisterRequest.getPassword());
        ServerResponse serverResponse = userEntity.register(userRegisterRequest.getName(), encryptedPassword, userRegisterRequest.getEmail(),
                DEFAULT_ACATAR);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }

        userRepository.save(userEntity);
        return ServerResponse.createBySuccess(userEntity);
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public ServerResponse<UserEntity> login(@Valid @NotBlank String username, @NotBlank String password) {
        UserEntity userEntity = userRepository.findByName(username);

        String encryptedPassword = encryptPassword(password);
        if (userEntity != null && userEntity.verifyPassword(encryptedPassword)) {
            return ServerResponse.createBySuccess(userEntity);
        }

        return ServerResponse.createByErrorMessage("用户名或密码错误");
    }

    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    public ServerResponse changePassword(@Valid UserChangePasswordRequest request) {
        UserEntity exitedUserEntity = userRepository.findByCode(request.getCode());
        if (exitedUserEntity == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String encryptedPassword = encryptPassword(request.getNewPassword());
        ServerResponse<UserEntity> serverResponse = exitedUserEntity.changePassword(request.getOriginPassword(), encryptedPassword);

        return serverResponse;
    }

    // 找回密码

    /**
     * 更新个人信息
     *
     * @param request
     * @return
     */
    public ServerResponse<UserEntity> updateProfile(UserUpdateInfoRequest request) {
        UserEntity userEntity = userRepository.findByCode(request.getCode());
        if (userEntity == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        if (StringUtils.isNotBlank(request.getName())) {
            UserEntity namedUserEntity = userRepository.findByName(request.getName());
            if (namedUserEntity != null) {
                return ServerResponse.createByErrorMessage("用户名已存在");
            }
        }

        ServerResponse<UserEntity> serverResponse = userEntity.updateProfile(request.getName(), request.getEmail(), request.getPhone());
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }

        userRepository.save(userEntity);
        return ServerResponse.createBySuccess(userEntity);
    }

    /**
     * 查询个人信息
     *
     * @param code
     * @return
     */
    public ServerResponse<UserEntity> getProfile(String code) {
        UserEntity userEntity = userRepository.findByCode(code);
        if (userEntity == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        return ServerResponse.createBySuccess(userEntity);
    }

    /**
     * 加密密码
     *
     * @param password
     * @return
     */
    private String encryptPassword(String password) {
        // 在这里使用BCrypt进行密码加密，这里只是示例，具体实现需要使用安全的加密算法
//        return BCrypt.hashpw(password, BCrypt.gensalt());
        return password;
    }

    /**
     * 校验密码
     * @param plainPassword
     * @param hashedPassword
     * @return
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
