package com.test.order.service.user;

import com.test.order.pojo.LoginUser;
import com.test.order.pojo.User;
import com.test.order.service.perms.interfacs.PermissionRepository;
import com.test.order.service.perms.interfacs.UserRoleRepository;
import com.test.order.service.user.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUserName(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("user not exist");
        }

        // 查询用户角色
        Boolean existsValidRole = userRoleRepository.existsValidRole(user.getId());
        if (!existsValidRole) {
            throw new RuntimeException("user permission insufficient");
        }

        // 查询权限值
        List<String> perms = permissionRepository.getPermsByUid(user.getId());
        return new LoginUser(user, perms);
    }


}
