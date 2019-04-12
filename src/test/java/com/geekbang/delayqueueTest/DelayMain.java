package com.geekbang.delayqueueTest;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * 延迟队列启动类
 *
 * @author 杨正
 */
@Slf4j
public class DelayMain {

    private static DelayQueue<DelayEvent> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {
        List<DelayEvent> list = new ArrayList<>();
        list.add(new DelayEvent("张三", LocalDateTime.now().plus(5, ChronoUnit.SECONDS)));
        list.add(new DelayEvent("李四", LocalDateTime.now().plus(11, ChronoUnit.SECONDS)));
        list.add(new DelayEvent("王五", LocalDateTime.now().plus(12, ChronoUnit.SECONDS)));
        list.forEach(delayEvent -> log.info("队列将要存放的数据：{}", delayEvent));
        /* 放入队列 */
        list.stream()
//                .sorted(Comparator.comparing(DelayEvent::getExcuteTime))
                .forEach(delayEvent -> delayQueue.offer(delayEvent));

        while (true) {
            DelayEvent take;
            try {
                take = delayQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
            log.info("取出来的数据：{}", take);
            log.info("---");
        }
    }
}
