package com.geekbang;

import com.geekbang.dao.CoffeeDao;
import com.geekbang.dao.CoffeeOrderDao;
import com.geekbang.enums.OrderEnum;
import com.geekbang.po.Coffee;
import com.geekbang.po.CoffeeOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 启动类
 *
 * @author 杨正
 */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PropertySource("classpath:config/config.properties")
public class SpringBucksApplication implements ApplicationRunner {

    private final CoffeeDao coffeeDao;
    private final CoffeeOrderDao coffeeOrderDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(ApplicationArguments args) {
        initOrder();
        findOrders();
    }

    private void findOrders() {
        coffeeDao.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(coffee -> log.info("loading... {}", coffee));

        List<CoffeeOrder> list = coffeeOrderDao.findTop3ByOrderByUpdateTimeDesc();
        list.forEach(coffeeOrder -> log.info("coffeeOrder {}", coffeeOrder));
        log.info("findTop3ByOrderByUpdateTimeDesc {}", getJoinOrder(list));

        list = coffeeOrderDao.findByCustomerOrderById("li si");
        log.info("findByCustomerOrderById {}", getJoinOrder(list));

        list.forEach(coffeeOrder -> {
            log.info("order {}", coffeeOrder);
            coffeeOrder.getItems()
                    .forEach(coffee -> log.info("coffee {}", coffee));
        });

        list = coffeeOrderDao.findByItemsName("latte");
        log.info("findByItemsName {}", getJoinOrder(list));
    }

    private String getJoinOrder(List<CoffeeOrder> list) {
        return list.stream()
                .map(coffeeOrder -> coffeeOrder.getId().toString())
                .collect(Collectors.joining(","));
    }

    private void initOrder() {
        Coffee latee = Coffee.builder()
                .name("latee")
                .price(Money.of(CurrencyUnit.of("CNY"), 20))
                .build();
        coffeeDao.save(latee);
        log.info("coffee {}", latee);
        Coffee civet = Coffee.builder()
                .name("civet")
                .price(Money.of(CurrencyUnit.of("CNY"), 50))
                .build();
        coffeeDao.save(civet);
        log.info("coffee {}", civet);
        CoffeeOrder zhangSan = CoffeeOrder.builder()
                .customer("zhang san")
                .items(Collections.singletonList(latee))
                .status(OrderEnum.BREWING)
                .build();
        coffeeOrderDao.save(zhangSan);
        log.info("order {}", zhangSan);
        CoffeeOrder liSi = CoffeeOrder.builder()
                .customer("li si")
                .items(Arrays.asList(latee, civet))
                .status(OrderEnum.INIT)
                .build();
        coffeeOrderDao.save(liSi);
        log.info("order {}", liSi);
    }

}
