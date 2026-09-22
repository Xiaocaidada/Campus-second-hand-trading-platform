package com.campusmarket.service;


import com.campusmarket.entity.Favorite;
import com.campusmarket.entity.FavoriteVO;
import com.campusmarket.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavorServiceImpl implements FavorService{
    @Autowired
    private FavoriteMapper favoriteMapper;
    public void addFavorite(Long goodsId, Long userId) {
        Favorite favorite = new Favorite();
        favorite.setGoodsId(goodsId);
        favorite.setUserId(userId);
        favoriteMapper.insert(favorite);
    }

    @Override
    public List<FavoriteVO> listLike(Long id){
        List<FavoriteVO> favoriteVOS = favoriteMapper.showLikes(id);
        favoriteVOS.forEach(s->s.setCreateTime(LocalDateTime.now()));
        return favoriteVOS;
    }
}
