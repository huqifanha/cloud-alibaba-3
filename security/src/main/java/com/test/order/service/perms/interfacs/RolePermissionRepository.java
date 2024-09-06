package com.test.order.service.perms.interfacs;



import com.baomidou.mybatisplus.extension.service.IService;
import com.test.order.pojo.RolePermission;

import java.util.List;

public interface RolePermissionRepository extends IService<RolePermission> {

    /**
     * 查询角色被赋予的权限
     */
    List<String> getRolePermissionIds(String roleId);

    void deleteRolePermissions(String roleId);

}
