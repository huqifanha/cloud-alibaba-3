package com.test.order.service.perms.interfacs;


import com.baomidou.mybatisplus.extension.service.IService;
import com.test.order.pojo.UserRole;

public interface UserRoleRepository extends IService<UserRole> {

    /**
     * 用户是否存在有效的角色
     */
    Boolean existsValidRole(String userId);

    /**
     * 用户是否已经被授予某个角色
     */
    UserRole getUserRole(String userId, String roleId);

}
