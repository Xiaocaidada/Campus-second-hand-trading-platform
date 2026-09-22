package com.campusmarket.service;

import com.campusmarket.mapper.StatisticsMapper;
import com.campusmarket.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Map<String, Object> getPlatformStatistics() {
        Map<String, Object> map = new HashMap<>();
        map.put("totalUsers", statisticsMapper.countUsers());
        map.put("totalGoods", statisticsMapper.countGoods());
        map.put("todayOrders", statisticsMapper.countTodayOrders());
        return map;
    }
}