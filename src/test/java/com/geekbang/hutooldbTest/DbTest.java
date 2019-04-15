package com.geekbang.hutooldbTest;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;

import java.sql.SQLException;
import java.util.List;

/**
 * hutool工具类-链接数据库
 *
 * @author 杨正
 */
public class DbTest {

    private static Db db;

    static {
        String URL = "jdbc:mysql://localhost:3306/spring_bucks?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=Asia/Shanghai";
        String USERNAME = "root";
        String PASSWORD = "root";
        db = Db.use(new SimpleDataSource(URL, USERNAME, PASSWORD));
    }

    public static void main(String[] args) {
        String sql = "select * from sms_push";
        try {
            List<Entity> query = db.query(sql);
            query.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
