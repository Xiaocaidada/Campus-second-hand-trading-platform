package com.campusmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campusmarket.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    void register(User user);
    User login(String username, String password);
    void updateStatus(Long userId, Integer status);

    List<User> listAll();
}