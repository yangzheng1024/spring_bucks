package com.geekbang.po;

import lombok.*;

import javax.persistence.*;

/**
 * 消息推送实体类
 */
@Entity
@Table(name = "sms_push")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsPush {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer type;

    @Column(name = "sms_type")
    private Integer smsType;

    private String title;

    private String content;

    @Column(name = "link_address")
    private String linkAddress;
}
