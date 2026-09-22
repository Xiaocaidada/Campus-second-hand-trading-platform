package com.campusmarket.controller;

import com.campusmarket.common.Result;
import com.campusmarket.entity.FavoriteVO;
import com.campusmarket.entity.Goods;
import com.campusmarket.mapper.GoodsMapper;
import com.campusmarket.service.FavorService;
import com.campusmarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavorController {
    @Autowired
    private FavorService favorService;
    @PostMapping("/{id}")
    public Result addFavorite(@PathVariable Long id, HttpServletRequest request) {
        favorService.addFavorite(id, (Long) request.getAttribute("userId"));
        return Result.ok("收藏成功");
    }
    @GetMapping("/list")
    public Result listLike(HttpServletRequest request) {
        List<FavoriteVO> favoriteVOS = favorService.listLike((Long)request.getAttribute("userId"));
        System.out.println(favoriteVOS);
        return Result.ok(favoriteVOS);
    }
}
