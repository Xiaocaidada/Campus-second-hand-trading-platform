package com.campusmarket.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdminOrderVO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String productTitle;

    private String username;

    private BigDecimal price;

    private String status;
}
