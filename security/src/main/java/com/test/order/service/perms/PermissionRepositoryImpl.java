package com.test.order.service.perms;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.order.mapper.PermissionMapper;
import com.test.order.pojo.Permission;
import com.test.order.service.perms.interfacs.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: hqf
 * @Date: 2023/2/21 16:42
 */
@Service
@Transactional
public class PermissionRepositoryImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionRepository {

    /**
     * 根据uid查询用户拥有的权限值
     */
    @Override
    public List<String> getPermsByUid(String userId) {
        return getBaseMapper().getPermsByUid(userId);
    }


    @Override
    public List<Permission> getAllPerms() {
        return getBaseMapper().selectList(null);
    }

}
