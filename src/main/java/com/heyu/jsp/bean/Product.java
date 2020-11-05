package com.heyu.jsp.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author heyu
 * @date 2020/11/5
 */
@Data
public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private String detail;
}
