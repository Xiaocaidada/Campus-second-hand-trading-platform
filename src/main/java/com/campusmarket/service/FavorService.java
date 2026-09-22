package com.campusmarket.service;

import com.campusmarket.entity.FavoriteVO;

import java.util.List;

public interface FavorService {
    void addFavorite(Long goodsId, Long userId);
    List<FavoriteVO> listLike(Long userId);
}
