package com.campusmarket.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusmarket.common.Result;
import com.campusmarket.entity.Goods;
import com.campusmarket.service.GoodsService;
import com.campusmarket.service.OrderService;
import com.campusmarket.service.StatisticsService;
import com.campusmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/goods/list")
    public Result listGoods(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(required = false) Long categoryId,
                            @RequestParam(required = false) String status) {
        Page<Goods> goodsPage = goodsService.adminSearch(page, size, keyword, categoryId, status);
        return Result.ok(goodsPage);
    }

    @PutMapping("/user/status")
    public Result updateUserStatus(@RequestBody Map<String, Object> map) {
        Long userId = Long.valueOf(map.get("userId").toString());
        Integer status = (Integer) map.get("status");
        userService.updateStatus(userId, status);
        return Result.ok(null);
    }

    @PutMapping("/goods/status")
    public Result updateGoodsStatus(@RequestBody Map<String, Object> map) {
        Long goodsId = Long.valueOf(map.get("goodsId").toString());
        String status = (String) map.get("status");
        goodsService.updateStatus(goodsId, status);
        return Result.ok(null);
    }

    @GetMapping("/statistics")
    public Result statistics() {
        return Result.ok(statisticsService.getPlatformStatistics());
    }

    @GetMapping("/user")
    public Result getUser(){
        int i =1;
        return Result.ok(userService.listAll());
    }

    @GetMapping("/order")
    public Result getGoods(){
        System.out.println("admin订单查询");
        return Result.ok(orderService.listAll());
    }
}