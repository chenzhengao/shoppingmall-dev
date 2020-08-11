package com.czg.controller;

import com.czg.pojo.Users;
import com.czg.pojo.bo.UserBO;
import com.czg.service.UserService;
import com.czg.utils.CookieUtils;
import com.czg.utils.JsonUtils;
import com.czg.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.czg.utils.JSONResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录",tags ="用于用户注册登录的相关接口" ) //swagger显示样式
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username) {

        // 1. 判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("用户名不可为空！");
        }

        // 2. 查找注册的用户名是否存在
        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("用户名已存在！");
        }

        // 3. 请求成功，用户名没有重复
        return JSONResult.ok();
    }


    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/regist")
    public JSONResult register(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response){

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 0. 判断用户名密码不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return JSONResult.errorMsg("用户名或者密码不能为空");
        }
        // 1. 查询使用名是否存在
        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("用户名已存在");
        }
        // 2. 判断密码的长度不能少于6位
        if (password.length() < 6) {
            return JSONResult.errorMsg("密码长度不能少于6");
        }
        // 3.  判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return JSONResult.errorMsg("两次密码输入不一致");
        }
        // 4. 实现注册
        Users result = userService.createUser(userBO);
        //清除隐私信息
        result = setNullProperty(result);

        // 3. cookie
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(result), true);
        return JSONResult.ok();
    }

    /**
     * 清除隐私信息
     *
     * @param result userResult
     * @return Users
     */
    private Users setNullProperty(Users result) {
        result.setPassword(null);
        result.setMobile(null);
        result.setEmail(null);
        result.setCreatedTime(null);
        result.setUpdatedTime(null);
        result.setBirthday(null);

        return result;
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // 0. 判断用户名密码不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("用户名或者密码不能为空");
        }

        // 1. 登录
        Users result = null;
        try {
            result = userService.queryUSerForLogin(username, MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 如果查询的信息为null
        if (result == null) {
            return JSONResult.errorMsg("用户名或者密码不正确");
        }

        // 2. 清除隐私信息
        result = setNullProperty(result);

        // 3. cookie
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(result), true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据

        return JSONResult.ok(result);


    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult login(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        //清除用户相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");
        //TODO 用户退出登录，需要清空购物车
        //TODO 分布式会话中需要清楚用户数据

        return JSONResult.ok();
    }

}
