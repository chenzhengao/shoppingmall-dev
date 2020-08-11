package com.czg;

import com.czg.mapper.UsersMapper;
import com.czg.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: shoppingmall-dev
 * @description:
 * @author: czg
 * @create: 2020-07-29 18:23
 */
public class UserServiceTest {

    @Autowired
    public UsersMapper usersMapper;

    @Test
    public void demo(){

        System.out.println(usersMapper);
    }
}
