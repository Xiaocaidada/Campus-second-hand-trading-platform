package com.campusmarket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campusmarket.entity.Goods;

import java.util.List;

public interface GoodsService extends IService<Goods> {
    void publish(Goods goods);
    Page<Goods> search(int page, int size, String keyword, Long categoryId);
    Page<Goods> adminSearch(int page, int size, String keyword, Long categoryId, String status);
    void updateStatus(Long goodsId, String status);
    List<Goods> listByUser(Long userId);
}