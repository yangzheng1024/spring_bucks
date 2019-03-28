package com.geekbang.dao;

import com.geekbang.po.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * coffee Dao
 *
 * @author 杨正
 */
public interface CoffeeDao extends JpaRepository<Coffee, Long> {

}
