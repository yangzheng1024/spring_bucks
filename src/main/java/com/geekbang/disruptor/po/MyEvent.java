package com.geekbang.disruptor.po;

import lombok.*;

/**
 * 自定义事件-创建事件
 *
 * @author 杨正
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyEvent {

    private Long eventId;

    private String message;
}
