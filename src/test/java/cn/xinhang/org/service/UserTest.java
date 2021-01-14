package cn.xinhang.org.service;

import cn.xinhang.basic.util.MD5Utils;
import cn.xinhang.user.domain.User;
import cn.xinhang.user.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTest extends  BaseTest {
    @Autowired
    private IUserService userService;

    @Test
    public void test() throws Exception {
        List<User> all = userService.getAll();
        all.forEach(user -> {
            user.setPassword(MD5Utils.encrypByMd5(user.getPassword() + user.getSalt()));
            userService.update(user);
        });
    }
}
