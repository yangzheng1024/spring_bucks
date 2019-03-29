package com.geekbang.core;

import org.springframework.stereotype.Component;

@Component("ipayPlanProcess")
public class IPayPlanProcess implements PlanProcess {

    @Override
    public void method01() {
        System.out.println("IPayPlanProcess.method01");
    }

    @Override
    public void method02() {
        System.out.println("IPayPlanProcess.method02");
    }
}
