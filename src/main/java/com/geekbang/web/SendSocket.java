package com.geekbang.web;

import cn.hutool.core.util.StrUtil;
import com.geekbang.websocket.WebSocketServer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author 杨正
 */
@Controller
@RequiredArgsConstructor
public class SendSocket {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 推送数据接口
     *
     * @param userId  用户id
     * @param message 发送的信息
     * @return 成功：success
     */
    @ResponseBody
    @RequestMapping("/socket/push/{userId}/{message}")
    public String pushToWeb(@PathVariable String userId, @PathVariable String message) {
        String sid = stringRedisTemplate.opsForValue().get(StrUtil.format(WebSocketServer.SESSION_ID_PREFIX, userId));
        if (StrUtil.isBlank(message)) {
            return "消息不能为空！";
        }
        try {
            WebSocketServer.sendInfo(message, sid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
