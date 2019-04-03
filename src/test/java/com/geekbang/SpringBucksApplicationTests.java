package com.geekbang;

import com.alibaba.druid.pool.DruidDataSource;
import com.geekbang.core.PlanProcess;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBucksApplicationTests {

    @Resource
    DataSource dataSource;

    @Value("${test}")
    String name;

    @Autowired
    Map<String, PlanProcess> map;

    /**
     * 查看当前使用的数据源，以及该数据源参数
     */
    @Test

    public void contextLoads() throws Exception {
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

        connection.close();
        System.out.println(name);

        PlanProcess ysePlanProcess = map.get("ysePlanProcess");
        PlanProcess ipayPlanProcess = map.get("ipayPlanProcess");
        log.info("ysePlanProcess:{}", ysePlanProcess);
        log.info("ipayPlanProcess:{}", ipayPlanProcess);
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        log.info("格式化当前时间：{}", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        long thisTime = now.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        log.info("时间戳当前时间：{}", thisTime);
    }

}
