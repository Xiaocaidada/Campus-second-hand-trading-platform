package com.campusmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campusmarket.entity.Favorite;
import com.campusmarket.entity.FavoriteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteMapper extends BaseMapper<Favorite> {


    @Select("select f.id,g.title AS goodsName,g.status,g.price,g.status,g.user_id AS sellorId from " +
            " favorite  f left join goods g ON f.goods_id= g.id  where f.user_id=#{id}")
    List<FavoriteVO> showLikes(Long id);
}
