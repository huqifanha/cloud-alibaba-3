package com.test.order.service.perms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.order.mapper.UserRoleMapper;
import com.test.order.pojo.UserRole;
import com.test.order.service.perms.interfacs.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hqf
 * @Date: 2023/5/31 11:42
 */
@Service
public class UserRoleRepositoryImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleRepository {

    @Override
    public Boolean existsValidRole(String userId) {
        List<UserRole> userRoles = getBaseMapper().userExistsValidRole(userId);
        return userRoles != null && userRoles.size() > 0;
    }

    @Override
    public UserRole getUserRole(String userId, String roleId) {
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query
                .eq("user_id", userId)
                .eq("role_id", roleId)
                .last("limit 1");
        return getBaseMapper().selectOne(query);
    }

}
