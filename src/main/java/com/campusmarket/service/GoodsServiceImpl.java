package com.campusmarket.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campusmarket.entity.Goods;
import com.campusmarket.mapper.GoodsMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Override
    public void publish(Goods goods) {
        goods.setStatus("PENDING");
        save(goods);
    }

    @Override
    public Page<Goods> search(int page, int size, String keyword, Long categoryId) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "NORMAL");
        if (StringUtils.hasText(keyword)) {
            wrapper.like("title", keyword);
        }
        if (categoryId != null) {
            wrapper.eq("category_id", categoryId);
        }
        wrapper.orderByDesc("create_time");
        return page(new Page<>(page, size), wrapper);
    }

    @Override
    public Page<Goods> adminSearch(int page, int size, String keyword, Long categoryId, String status) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(status)) {
            wrapper.eq("status", status);
        }
        if (StringUtils.hasText(keyword)) {
            wrapper.like("title", keyword);
        }
        if (categoryId != null) {
            wrapper.eq("category_id", categoryId);
        }
        wrapper.orderByDesc("create_time");
        return page(new Page<>(page, size), wrapper);
    }

    @Override
    public void updateStatus(Long goodsId, String status) {
        Goods goods = getById(goodsId);
        if (goods == null) throw new RuntimeException("商品不存在");
        goods.setStatus(status);
        updateById(goods);
    }

    @Override
    public List<Goods> listByUser(Long userId) {
        return lambdaQuery().eq(Goods::getUserId, userId).list();
    }
}