package com.geekbang.dao.base;

import com.geekbang.po.CoffeeOrder;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * base dao
 *
 * @author 杨正
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {

    List<CoffeeOrder> findTop3ByOrderByUpdateTimeDesc();
}
