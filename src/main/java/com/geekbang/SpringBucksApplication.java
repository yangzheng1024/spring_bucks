package com.geekbang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 *
 * @author 杨正
 */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class SpringBucksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }


}
