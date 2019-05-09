package com.geekbang.disruptor.api;

import com.geekbang.disruptor.MyEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * disruptor的api
 *
 * @author 杨正
 */
@RestController
@Slf4j
public class DisruptorApi {

    private final MyEventService myEventService;

    @Autowired
    public DisruptorApi(MyEventService myEventService) {
        this.myEventService = myEventService;
    }

    @GetMapping("/test")
    public String test() {
        log.info("=====");
        myEventService.sendNotify("hello world");
        return "hello world";
    }
}
