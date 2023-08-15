package top.jarry.personal.presentation.web;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jarry.personal.application.dto.request.UserRegisterRequest;
import top.jarry.personal.application.dto.request.UserUpdateInfoRequest;
import top.jarry.personal.application.dto.response.UserInfoResponse;
import top.jarry.personal.application.service.UserService;
import top.jarry.personal.infrastructure.common.ServerResponse;
import top.jarry.personal.infrastructure.util.CookieUtil;

import static top.jarry.personal.infrastructure.common.Constants.CURRENT_USER;

/**
 * @Description 用户控制器
 * @Date 2023/8/6 22:18
 * @Author king
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param code
     * @return
     */
    @GetMapping("/info")
    public ServerResponse<UserInfoResponse> getUserInfo(String code, HttpServletRequest servletRequest) {
        String loginToken = CookieUtil.readLoginToken(servletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        return userService.getUserInfo(code);
    }

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @GetMapping("/register")
    public ServerResponse<UserInfoResponse> register(UserRegisterRequest request, HttpServletResponse servletResponse,HttpSession session) {
        ServerResponse<UserInfoResponse> serverResponse = userService.register(request);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        UserInfoResponse userInfoResponse = serverResponse.getData();
        if (userInfoResponse == null) {
            return ServerResponse.createByErrorMessage("登录失败-用户信息为空");
        }
        //登录成功，将用户信息存入session
        session.setAttribute(CURRENT_USER, userInfoResponse);
        CookieUtil.writeLoginToken(servletResponse, session.getId());
        return serverResponse;
    }

    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     */
    @GetMapping("/login")
    public ServerResponse<UserInfoResponse> login(String name, String password, HttpSession session, HttpServletResponse servletResponse) {
        ServerResponse<UserInfoResponse> serverResponse = userService.login(name, password);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        UserInfoResponse userInfoResponse = serverResponse.getData();
        if (userInfoResponse == null) {
            return ServerResponse.createByErrorMessage("登录失败-用户信息为空");
        }

        //登录成功，将用户信息存入session
        session.setAttribute(CURRENT_USER, userInfoResponse);
        CookieUtil.writeLoginToken(servletResponse, session.getId());

        return serverResponse;
    }

    /**
     * 更新用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/updateInfo")
    public ServerResponse<UserInfoResponse> updateInfo(UserUpdateInfoRequest request, HttpServletRequest servletRequest) {
        String loginToken = CookieUtil.readLoginToken(servletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        return userService.updateUserInfo(request);
    }

    /**
     * 注销
     * 网页端只有登录状态，才会有注销
     *
     * @return
     */
    public ServerResponse logout(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        String loginToken = CookieUtil.readLoginToken(servletRequest);
        CookieUtil.delLoginToken(servletRequest,servletResponse);
        return userService.logout();
    }
}
