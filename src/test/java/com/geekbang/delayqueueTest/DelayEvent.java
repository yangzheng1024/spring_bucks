package com.geekbang.delayqueueTest;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * delay延迟对象
 *
 * @author 杨正
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DelayEvent implements Delayed {

    /**
     * 用户名
     */
    private String name;

    /**
     * 执行时间
     */
    private LocalDateTime excuteTime;

    /**
     * @param unit 单位参数
     * @return 到激活日期的剩余时间 时间单位由单位参数指定
     */
    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long thisTime = getExcuteTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return unit.convert(thisTime - now, TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed delayed) {
        DelayEvent delayEvent = (DelayEvent) delayed;
        return getExcuteTime().compareTo(delayEvent.getExcuteTime());
    }
}
