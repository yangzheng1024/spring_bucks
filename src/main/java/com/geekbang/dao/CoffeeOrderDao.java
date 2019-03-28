package com.geekbang.dao;

import com.geekbang.dao.base.BaseRepository;
import com.geekbang.po.CoffeeOrder;

import java.util.List;

/**
 * coffeeOrder Dao
 *
 * @author 杨正
 */
public interface CoffeeOrderDao extends BaseRepository<CoffeeOrder, Long> {

    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItemsName(String name);
}
