package com.test.order.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.order.pojo.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> getPermsByUid(String userId);

}
