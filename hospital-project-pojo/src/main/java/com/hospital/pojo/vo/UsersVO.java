package com.hospital.pojo.vo;

import lombok.Data;

/**
 * @program: foodie-dev
 * @description:
 * @author: wty
 * @create: 2020-03-16 19:30
 */
@Data
public class UsersVO {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户会话token
     */
    private String userUniqueToken;
}
