package com.hospital.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: foodie-dev
 * @description: 接收前端注册用户信息
 * @author: wty
 * @create: 2020-02-13 01:12
 */
@ApiModel(value = "用户对象BO", description = "接收前端传过来的用户信息")
@Data
public class UserBo {

    @ApiModelProperty(value = "用户名", name = "username", example = "imooc", required = true)
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456", required = false)
    private String confirmPassword;
}
