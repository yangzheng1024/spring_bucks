package com.geekbang.po;

import com.geekbang.base.BaseEntity;
import com.geekbang.enums.OrderEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 订单实体类
 *
 * @author 杨正
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_order")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder extends BaseEntity {

    private String customer;

    @ManyToMany
    @JoinTable(name = "t_order_coffee")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderEnum status;
}
