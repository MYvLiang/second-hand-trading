package com.second.hand.trading.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.UserModel;
import com.second.hand.trading.server.service.UserService;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-12-17  10:06
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @GetMapping("getAll")
    public ResultVo getAllUsers() {
        return ResultVo.success(userService.getUserList());
    }

    @PostMapping("sign-in")
    public ResultVo signIn(@RequestBody String message) {
        UserModel userModel = null;
        try {
            userModel = JSONObject.parseObject(message, UserModel.class);
        } catch (Exception e) {
            //参数解析错误
            return ResultVo.fail(ErrorMsg.JSON_READ_ERROR);
        }
        userModel.setSignInTime(new Timestamp(System.currentTimeMillis()));
        if (userModel.getAvatar() == null || "".equals(userModel.getAvatar())) {
            userModel.setAvatar("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        }
        if (userService.userSignIn(userModel)) {
            return ResultVo.success(userModel);
        }
        return ResultVo.fail(ErrorMsg.REGISTER_ERROR);
    }

    /**
     * 登录，不安全，可伪造id，后期改进
     *
     * @param accountNumber
     * @param userPassword
     * @param response
     * @return
     */
    @RequestMapping("login")
    public ResultVo login(@RequestParam("accountNumber") @NotEmpty @NotNull String accountNumber,
                          @RequestParam("userPassword") @NotEmpty @NotNull String userPassword,
                          HttpServletResponse response) {
        UserModel userModel = userService.userLogin(accountNumber, userPassword);
        System.out.println("登录：" + userModel);
        if (null == userModel) {
            return ResultVo.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }
        Cookie cookie = new Cookie("shUserId", String.valueOf(userModel.getId()));
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResultVo.success(userModel);
    }

    /**
     * 退出登录
     *
     * @param shUserId
     * @param response
     * @return
     */
    @RequestMapping("logout")
    public ResultVo logout(@CookieValue("shUserId")
                           @NotNull(message = "登录异常 请重新登录")
                           @NotEmpty(message = "登录异常 请重新登录") String shUserId, HttpServletResponse response) {
        Cookie cookie = new Cookie("shUserId", shUserId);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResultVo.success();
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("info")
    public ResultVo getOneUser(@CookieValue("shUserId") @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录")
                                       String id) {
        return ResultVo.success(userService.getUser(Long.valueOf(id)));
    }

    @GetMapping("/nickname")
    public ResultVo updateUserNickname(@CookieValue("shUserId") @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录")
                                               String id, @RequestParam("nickname") @NotEmpty @NotNull String nickname) {
        UserModel userModel = new UserModel();
        userModel.setNickname(nickname);
        userModel.setId(Long.valueOf(id));
        if (userService.updateNickname(userModel)) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/password")
    public ResultVo updateUserPassword(@CookieValue("shUserId") @NotNull(message = "登录异常 请重新登录")
                                       @NotEmpty(message = "登录异常 请重新登录") String id,
                                       @RequestParam("oldPassword") @NotEmpty @NotNull String oldPassword,
                                       @RequestParam("newPassword") @NotEmpty @NotNull String newPassword) {
        if (userService.updatePassword(newPassword,oldPassword,Long.valueOf(id))) {
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.PASSWORD_RESET_ERROR);
    }
}
