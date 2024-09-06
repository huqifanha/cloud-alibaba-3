package com.test.order.service.perms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.order.mapper.RolePermissionMapper;
import com.test.order.pojo.RolePermission;
import com.test.order.service.perms.interfacs.RolePermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: hqf
 * @Date: 2023/5/31 11:42
 */
@Service
public class RolePermissionRepositoryImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionRepository {


    @Override
    public List<String> getRolePermissionIds(String roleId) {
        QueryWrapper<RolePermission> query = new QueryWrapper<>();
        query.eq("role_id", roleId);
        return getBaseMapper().selectList(query).stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
    }

    @Override
    public void deleteRolePermissions(String roleId) {
        QueryWrapper<RolePermission> query = new QueryWrapper<>();
        query.eq("role_id", roleId);
        getBaseMapper().delete(query);
    }

}
