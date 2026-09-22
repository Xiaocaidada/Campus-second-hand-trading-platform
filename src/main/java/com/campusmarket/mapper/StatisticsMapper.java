package com.campusmarket.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.Map;

@Mapper
public interface StatisticsMapper {
    @Select("SELECT COUNT(*) FROM user")
    Long countUsers();

    @Select("SELECT COUNT(*) FROM goods WHERE status = 'NORMAL'")
    Long countGoods();

    @Select("SELECT COUNT(*) FROM `order` WHERE DATE(create_time) = CURDATE()")
    Long countTodayOrders();
}