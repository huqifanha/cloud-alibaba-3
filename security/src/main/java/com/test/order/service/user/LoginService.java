package com.test.order.service.user;

import com.test.order.pojo.LoginUser;
import com.test.order.pojo.User;
import com.test.order.service.user.interfaces.UserRepository;
import com.test.order.utils.JwtUtil;
import com.test.order.utils.redis.RedisCache;
import com.test.order.utils.redis.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hqf
 * @Date: 2023/2/21 16:42
 */
@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public HashMap<String, String> loginManagement(User user) {
        HashMap<String, String> map = new HashMap<>();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)) {
            map.put("msg", "user not login");
            return map;
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(userId, JwtUtil.JWT_TTL);

        // authenticate用户信息存入redis
        String loginUserKey = RedisKey.LOGIN_USER.combKey(userId);
        redisCache.setCacheObject(loginUserKey, loginUser, 20, TimeUnit.SECONDS);

        // 把token响应给前端，后续所有的接口都必须携带token才能访问
        map.put("msg", "success");
        map.put("token", jwt);
        return map;
    }

    public String logoutManagement() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "success";
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        redisCache.deleteObject(RedisKey.LOGIN_USER.combKey(loginUser.getUser().getId()));
        return "success";
    }

    public String logout(String accessToken) {
        return "success";
    }

    public HashMap<String, String> login(User loginUser) {
        HashMap<String, String> map = new HashMap<>();

        User user = userRepository.getByUserName(loginUser.getUsername());
        if (Objects.isNull(user)) {
            map.put("msg", "user not exist");
            return map;
        }
        if (!passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            map.put("msg", "password error");
            return map;
        }
        String userId = user.getId();
        String jwt = JwtUtil.createJWT(userId, JwtUtil.JWT_TTL);
        // 把token响应给前端，后续所有的接口都必须携带token才能访问
        map.put("msg", "success");
        map.put("token", jwt);
        return map;
    }

    public String resister(User resister) {
        User user = userRepository.getByUserName(resister.getUsername());
        if (user != null) {
            return "user exist";
        }

        user = new User();
        user.setUsername(resister.getUsername());
        user.setPassword(passwordEncoder.encode(resister.getPassword()));
        userRepository.save(user);

        return "success";
    }

}
