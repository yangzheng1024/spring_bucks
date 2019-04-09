package com.geekbang.dao;

import com.geekbang.po.SmsPush;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 杨正
 */
public interface SmsPushDao extends JpaRepository<SmsPush, Long> {

}
