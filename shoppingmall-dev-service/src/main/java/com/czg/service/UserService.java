package com.czg.service;

import com.czg.pojo.Users;
import com.czg.pojo.bo.UserBO;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean queryUserNameIsExist(String username);

    /**
     * 新建用户
     * @param userBO
     * @return
     */
    public Users createUser(UserBO userBO);
    /**
     * 检索用户名和密码是否匹配
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Users queryUSerForLogin(String username, String password);
}
