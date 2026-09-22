package com.campusmarket.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campusmarket.entity.AdminOrderVO;
import com.campusmarket.entity.Goods;
import com.campusmarket.entity.MyOrderVO;
import com.campusmarket.entity.Order;
import com.campusmarket.mapper.OrderMapper;
import com.campusmarket.service.GoodsService;
import com.campusmarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public void create(Order order) {
        Goods goods = goodsService.getById(order.getGoodsId());
        if (goods == null || !"NORMAL".equals(goods.getStatus())) {
            throw new RuntimeException("商品已下架或不存在");
        }
        if (goods.getUserId().equals(order.getBuyerId())) {
            throw new RuntimeException("不能购买自己的商品");
        }
        order.setSellerId(goods.getUserId());
        // 生成订单，商品状态保持正常（交易后续可做状态变更）
        save(order);
    }

    @Override
    public List<MyOrderVO> listByUser(Long userId) {
        return orderMapper.selectMyOrderList(userId);
    }

    @Override
    public List<AdminOrderVO> listAll() {
        List<AdminOrderVO> data= orderMapper.selectOrderWithGoodsAndUser();
        System.out.println(data);
        return data;
    }
}