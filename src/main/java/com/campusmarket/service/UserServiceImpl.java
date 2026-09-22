package com.campusmarket.service;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campusmarket.entity.User;
import com.campusmarket.mapper.UserMapper;
import com.campusmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        // 检查用户名是否存在
        if (lambdaQuery().eq(User::getUsername, user.getUsername()).count() > 0) {
            throw new RuntimeException("用户名已存在");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        save(user);
    }

    @Override
    public User login(String username, String password) {
        User user = lambdaQuery().eq(User::getUsername, username).one();
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        if (user.getStatus() == 0) {
            throw new RuntimeException("账号已被禁用");
        }
        return user;
    }

    @Override
    public void updateStatus(Long userId, Integer status) {
        User user = getById(userId);
        if (user == null) throw new RuntimeException("用户不存在");
        user.setStatus(status);
        updateById(user);
    }


    @Override
    public List<User> listAll() {
        return list();
    }
}