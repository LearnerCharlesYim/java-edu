package com.charles.health.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 预约设置
 */
@Data
@Entity
@Table(name = "t_ordersetting")
public class OrderSetting implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    private Date orderDate;//预约设置日期

    private Integer number;//可预约人数

    private Integer reservations ;//已预约人数
}
