package com.geekbang.disruptor;

import cn.hutool.core.lang.UUID;
import com.geekbang.disruptor.po.MyEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件消费者-此处用于处理业务逻辑
 *
 * @author 杨正
 */
@Slf4j
public class MyEventHandler implements EventHandler<MyEvent>, WorkHandler<MyEvent> {

    @Override
    public void onEvent(MyEvent myEvent, long l, boolean b) throws Exception {
        log.info("接收到消息");
        this.onEvent(myEvent);
    }

    @Override
    public void onEvent(MyEvent myEvent) {
        log.info("myEvent：{}，uuid：{}", myEvent, UUID.randomUUID().toString());
    }

}
