package com.test.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.order.pojo.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRole> userExistsValidRole(String userId);

}
