package com.campusmarket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MyOrderVO {
    // 订单号（订单主键id）
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    // 商品名称（关联goods表title）
    private String goodsTitle;
    // 订单金额
    private BigDecimal price;
    // 订单状态字符串（WAIT\_DELIVER / FINISHED / CANCEL）
    private String status;
    // 订单创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}