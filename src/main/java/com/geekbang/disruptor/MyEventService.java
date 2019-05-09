package com.geekbang.disruptor;

import com.geekbang.disruptor.po.MyEvent;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

/**
 * 整合Spring，对Disruptor进行初始化
 *
 * @author 杨正
 */
@Service
public class MyEventService implements DisposableBean, InitializingBean {

    private Disruptor<MyEvent> disruptor;
    private static final int RING_BUFFER_SIZE = 1024 * 1024;


    @Override
    public void destroy() {
        disruptor.shutdown();
    }

    @Override
    public void afterPropertiesSet() {
        disruptor = new Disruptor<MyEvent>(MyEventFactory.builder().build(), RING_BUFFER_SIZE, Executors.defaultThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new MyEventHandlerException());
        disruptor.handleEventsWith(new MyEventHandler());
        disruptor.start();
    }

    public void sendNotify(String message) {
        RingBuffer<MyEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent((event, sequence, data) -> event.setMessage(data), message);
    }
}
