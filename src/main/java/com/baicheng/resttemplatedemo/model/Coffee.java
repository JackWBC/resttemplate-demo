package com.baicheng.resttemplatedemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author baicheng
 * @description
 * @create 2019-03-24 16:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {
    private static final long serialVersionUID = -7614246110839501778L;

    private Long id;

    private String name;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;
}
