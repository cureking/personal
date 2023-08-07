package top.jarry.personal.presentation.web;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jarry.personal.application.dto.request.UserRegisterRequest;
import top.jarry.personal.application.dto.request.UserUpdateInfoRequest;
import top.jarry.personal.application.dto.response.UserInfoResponse;
import top.jarry.personal.application.service.UserService;
import top.jarry.personal.infrastructure.common.ServerResponse;

/**
 * @Description TODO
 * @Date 2023/8/6 22:18
 * @Author king
 */
@RestController("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     * @param code
     * @return
     */
    @GetMapping("/info")
    public ServerResponse<UserInfoResponse> getUserInfo(String code) {
        return userService.getUserInfo(code);
    }

    /**
     * 注册
     * @param request
     * @return
     */
    @GetMapping("/register")
    public ServerResponse<UserInfoResponse> register(UserRegisterRequest request) {
        return userService.register(request);
    }

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    @GetMapping("/login")
    public ServerResponse<UserInfoResponse> login(String name, String password) {
        return userService.login(name, password);
    }

    /**
     * 更新用户信息
     * @param request
     * @return
     */
    @GetMapping("/updateInfo")
    public ServerResponse<UserInfoResponse> updateInfo(UserUpdateInfoRequest request) {
        return userService.updateUserInfo(request);
    }

    /**
     * 注销
     * 网页端只有登录状态，才会有注销
     * @return
     */
    public ServerResponse logout() {
        return userService.logout();
    }
}
