package com.geekbang.core;

import org.springframework.stereotype.Component;

@Component
public class YsePlanProcess implements PlanProcess {

    @Override
    public void method01() {
        System.out.println("YsePlanProcess.method01");
    }

    @Override
    public void method02() {
        System.out.println("YsePlanProcess.method02");
    }
}
