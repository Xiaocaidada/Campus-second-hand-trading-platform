package com.campusmarket.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campusmarket.common.Result;
import com.campusmarket.entity.Goods;
import com.campusmarket.entity.Order;
import com.campusmarket.service.GoodsService;
import com.campusmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/publish")
    public Result publish(@RequestBody Goods goods, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        goods.setUserId(userId);
        goodsService.publish(goods);
        return Result.ok(null);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Long categoryId) {
        Page<Goods> goodsPage = goodsService.search(page, size, keyword, categoryId);
        return Result.ok(goodsPage);
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        // 直接根据 ID 查询，返回对象
        Goods goods = goodsService.getById(id);
        if (goods == null) {
            return Result.fail(404, "商品不存在");
        }
        return Result.ok(goods);
    }

    @PostMapping("/order")
    public Result createOrder(@RequestBody Order order, HttpServletRequest request) {
        Long buyerId = (Long) request.getAttribute("userId");
        order.setBuyerId(buyerId);
        orderService.create(order);
        return Result.ok(null);
    }

    @GetMapping("/my")
    public Result myGoods(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return Result.ok(goodsService.listByUser(userId));
    }
}