package com.test.order.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.order.mapper.UserMapper;
import com.test.order.pojo.User;
import com.test.order.service.user.interfaces.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @Author hqf
 * @Date 2024/9/5
 */
@Service
public class UserRepositoryImpl extends ServiceImpl<UserMapper, User> implements UserRepository {

    @Override
    public User getByUserName(String username) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query
                .eq("username", username)
                .last("limit 1");
        return getBaseMapper().selectOne(query);
    }


}
