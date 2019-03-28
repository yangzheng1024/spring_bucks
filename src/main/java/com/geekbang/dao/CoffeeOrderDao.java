package com.geekbang.dao;

import com.geekbang.po.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * coffeeOrder Dao
 *
 * @author 杨正
 */
public interface CoffeeOrderDao extends JpaRepository<CoffeeOrder, Long> {

}
