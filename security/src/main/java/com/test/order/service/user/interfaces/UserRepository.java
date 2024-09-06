package com.test.order.service.user.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.order.pojo.User;


public interface UserRepository extends IService<User> {

    public User getByUserName(String username);

}
