package com.charles.health.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 体检预约信息
 */
@Data
@Entity
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Member member;//会员

    private Date orderDate;//预约日期

    @Enumerated(EnumType.STRING)
    private OrderType orderType;//预约类型 电话预约/微信预约

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;//预约状态（是否到诊）

    @ManyToOne
    private Setmeal setmeal;//体检套餐id

    public enum OrderType {
        ORDER_TYPE_TELEPHONE("电话预约"),
        ORDER_TYPE_WEIXIN("微信预约");

        private String type;
        OrderType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
        public void setType(String message) {
            this.type = message;
        }
    }

    public enum OrderStatus {
        ORDER_STATUS_YES("已到诊"),
        ORDER_STATUS_NO("未到诊");

        private String status;
        OrderStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
    }
}
