package com.hospital.service;

import com.hospital.pojo.HospitalUser;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: hospital-project
 * @description: 用户
 * @author: wty
 * @create: 2020-06-07 09:51
 */
public interface UserService {

    @Transactional(propagation = Propagation.SUPPORTS)
    boolean queryUserNameIsExist(String username);

    @Transactional(propagation = Propagation.SUPPORTS)
    HospitalUser queryUserForLogin(String username, String password);
}
