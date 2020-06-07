package com.hospital.service.impl;

import com.hospital.mapper.HospitalUserMapper;
import com.hospital.pojo.HospitalUser;
import com.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @program: hospital-project
 * @description: 用户
 * @author: wty
 * @create: 2020-06-07 09:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HospitalUserMapper userMapper;

    /**
     * 检测用户名是否重复
     *
     * @param username
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExist(String username) {
        Example example = new Example(HospitalUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nickname", username);
        HospitalUser hospitalUsers = userMapper.selectOneByExample(example);
        if (hospitalUsers == null) {
            return false;
        }
        return true;
    }

    /**
     * 查询用户并登陆
     * @param username
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public HospitalUser queryUserForLogin(String username, String password) {
        Example userExample = new Example(HospitalUser.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("nickname", username);
        userCriteria.andEqualTo("password", password);
        HospitalUser userResult = userMapper.selectOneByExample(userExample);
        return userResult;
    }
}
