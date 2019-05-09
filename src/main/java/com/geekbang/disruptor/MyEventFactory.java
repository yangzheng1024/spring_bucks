package com.geekbang.disruptor;

import com.geekbang.disruptor.po.MyEvent;
import com.lmax.disruptor.EventFactory;
import lombok.Builder;

/**
 * 消息生产工厂-创建消息工厂用于生产消息
 *
 * @author 杨正
 */
@Builder
public class MyEventFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return MyEvent.builder().build();
    }
}
