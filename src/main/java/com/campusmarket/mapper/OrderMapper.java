package com.campusmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campusmarket.entity.AdminOrderVO;
import com.campusmarket.entity.MyOrderVO;
import com.campusmarket.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {


    /**
     * 后台查看订单信息
     */
    @Select("SELECT " +
            "o.id, g.title AS productTitle, u.username, g.price, o.status " +
            "FROM `order` o " +
            "LEFT JOIN goods g ON o.goods_id = g.id " +
            "LEFT JOIN user u ON o.buyer_id = u.id")
    List<AdminOrderVO> selectOrderWithGoodsAndUser();


    /**
     * 买家查看订单信息
     */
    @Select("SELECT o.id, g.title AS goodsTitle, g.price, o.status, o.create_time AS createTime " +
            "FROM `order` o " +
            "LEFT JOIN goods g ON o.goods_id = g.id " +
            "WHERE o.buyer_id = #{buyerId} " +
            "ORDER BY o.create_time DESC")
    List<MyOrderVO> selectMyOrderList(@Param("buyerId") Long buyerId);

}