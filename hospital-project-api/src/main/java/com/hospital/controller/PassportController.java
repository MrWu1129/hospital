package com.hospital.controller;

import com.hospital.pojo.HospitalUser;
import com.hospital.pojo.bo.UserBo;
import com.hospital.pojo.vo.UsersVO;
import com.hospital.service.UserService;
import com.hospital.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @program: hospital-project
 * @description: 用户注册、登陆、退出
 * @author: wty
 * @create: 2020-06-06 23:23
 */
@Api(value = "注册登陆退出",tags = {"用于注册登陆退出的相关接口"})
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @Autowired
    public RedisOperator redisOperator;

    public static final String REDIS_USER_TOKEN = "redis_user_token";

    @ApiOperation(value = "检测用户名是否重复", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public ResultDTO usernameIsExist(@RequestParam String username) {
        // 判断用户名不为空
        if (StringUtils.isBlank(username)) {
            return ResultDTO.errorMsg("用户名不能为空");
        }
        // 查找用户名是否为空
        boolean isExist = userService.queryUserNameIsExist(username);
        if (isExist) {
            return ResultDTO.errorMsg("用户名已存在");
        }
        // 请求成功，用户名没有重复
        return ResultDTO.ok();
    }

    @ApiOperation(value = "用户登陆", notes = "用户登陆", httpMethod = "POST")
    @PostMapping("/login")
    public ResultDTO login(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = userBo.getUsername();
        String password = userBo.getPassword();

        // 1、判断用户名和密码不能为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return ResultDTO.errorMsg("用户名或密码不能为空");
        }
        // 5、保存数据库
        HospitalUser userResult = userService.queryUserForLogin(username, password);
        if (userResult == null) {
            return ResultDTO.errorMsg("用户名或密码不正确");
        }
        String uniqueToken = UUID.randomUUID().toString().trim();
        // 实现用户redis会话
        redisOperator.set(REDIS_USER_TOKEN+userResult.getId(),uniqueToken);
        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(userResult,usersVO);
        usersVO.setUserUniqueToken(uniqueToken);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(usersVO), true);
        return ResultDTO.ok(usersVO);
    }

    @ApiOperation(value = "退出登陆", notes = "退出登陆", httpMethod = "POST")
    @PostMapping("/logout")
    public ResultDTO logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1、清楚cookie
        CookieUtils.deleteCookie(request, response, "user");
        // 3、分布式会话清除用户数据
        redisOperator.del(REDIS_USER_TOKEN+":"+userId);
        return ResultDTO.ok();
    }
}