package com.test.order.service.perms;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.order.mapper.RoleMapper;
import com.test.order.pojo.Role;
import com.test.order.service.perms.interfacs.RoleRepository;
import org.springframework.stereotype.Service;


@Service
public class RoleRepositoryImpl extends ServiceImpl<RoleMapper, Role> implements RoleRepository {


}
