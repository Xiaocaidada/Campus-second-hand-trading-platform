package com.campusmarket.controller;

import com.campusmarket.common.Result;
import com.campusmarket.entity.User;
import com.campusmarket.service.UserService;
import com.campusmarket.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result register(@RequestBody @Valid User user) {
        userService.register(user);
        return Result.ok(null);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        User user = userService.login(map.get("username"), map.get("password"));
        String token = jwtUtil.createToken(user.getId(), user.getRole());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);
        return Result.ok(data);
    }

    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return Result.ok(userService.getById(userId));
    }
}