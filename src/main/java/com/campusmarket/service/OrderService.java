package com.campusmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campusmarket.entity.AdminOrderVO;
import com.campusmarket.entity.MyOrderVO;
import com.campusmarket.entity.Order;

import java.util.List;

public interface OrderService extends IService<Order> {
    void create(Order order);
    List<MyOrderVO> listByUser(Long userId);

    List<AdminOrderVO> listAll();
}