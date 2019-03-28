package com.geekbang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 *
 * @author 杨正
 */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@PropertySource("classpath:config/config.properties")
public class SpringBucksApplication implements ApplicationRunner {

    @Value("${test}")
    String name;

    public static void main(String[] args) {
        SpringApplication.run(SpringBucksApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        getName();
    }

    private void getName() {
        log.info(name);
    }
}
