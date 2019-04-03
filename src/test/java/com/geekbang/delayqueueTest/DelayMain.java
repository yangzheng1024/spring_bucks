package com.geekbang.delayqueueTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * 延迟队列启动类
 *
 * @author 杨正
 */
public class DelayMain {

    private static DelayQueue<DelayEvent> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {
        List<DelayEvent> list = new ArrayList<>();
        list.add(new DelayEvent("张三", LocalDateTime.now().minus(1, ChronoUnit.MINUTES)));
        list.add(new DelayEvent("李四", LocalDateTime.now().minus(2, ChronoUnit.MINUTES)));
        list.add(new DelayEvent("王五", LocalDateTime.now().minus(3, ChronoUnit.MINUTES)));
        /* 放入队列 */
        list.stream()
                .sorted(Comparator.comparing(DelayEvent::getExcuteTime))
                .forEach(delayEvent -> delayQueue.offer(delayEvent));

        while (true) {
            DelayEvent take;
            try {
                take = delayQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
            System.out.println(take);
            System.out.println("------");
        }
    }
}
