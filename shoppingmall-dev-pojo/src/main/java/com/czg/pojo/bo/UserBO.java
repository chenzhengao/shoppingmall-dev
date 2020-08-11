package com.czg.pojo.bo;

/**
 * @program: shoppingmall-dev
 * @description: 接收注册/登录页面的数据实体类BO
 * @author: czg
 * @create: 2020-07-30 12:57
 */
public class UserBO {

    private String username;

    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
