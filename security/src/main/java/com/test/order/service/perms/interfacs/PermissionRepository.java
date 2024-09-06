package com.test.order.service.perms.interfacs;




import com.baomidou.mybatisplus.extension.service.IService;
import com.test.order.pojo.Permission;

import java.util.List;

public interface PermissionRepository extends IService<Permission> {

    List<String> getPermsByUid(String userId);

    List<Permission> getAllPerms();

}
