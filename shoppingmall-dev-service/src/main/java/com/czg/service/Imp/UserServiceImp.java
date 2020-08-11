package com.czg.service.Imp;

import com.czg.ennum.Sex;
import com.czg.mapper.UsersMapper;
import com.czg.pojo.Users;
import com.czg.pojo.bo.UserBO;
import com.czg.service.UserService;
import com.czg.utils.DateUtil;
import com.czg.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @program: shoppingmall-dev
 * @description:
 * @author: czg
 * @create: 2020-07-29 17:46
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private Sid sid;//主键ID生成器

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Transactional(propagation = Propagation.SUPPORTS)//事务传播行为SUPPORTS：如果有事务就使用事务，如果没有也不会开启事务
    @Override
    public boolean queryUserNameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);

        Users result = usersMapper.selectOneByExample(userExample);

        return result == null ? false : true;
    }

    @Transactional(propagation = Propagation.REQUIRED) //事务传播行为REQUIRED：如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。
    @Override
    public Users createUser(UserBO userBO) {
        Users user=new Users();

        String userId=sid.nextShort();//生成主键ID
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            //密码需要加密
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认昵称与用户名一致
        user.setNickname(userBO.getUsername());
        //默认用户头像
        user.setFace(USER_FACE);
        // 默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 默认性别为 保密
        user.setSex(Sex.secret.type);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Users queryUSerForLogin(String username, String password) {

        //建立一个example 方便查询
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();

        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);

        Users result = usersMapper.selectOneByExample(userExample);

        return result;
    }

}
