package com.geekbang.web;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.geekbang.dao.SmsPushDao;
import com.geekbang.po.SmsPush;
import com.geekbang.websocket.WebSocketServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author 杨正
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class SendSocket {

    private final StringRedisTemplate stringRedisTemplate;
    private final SmsPushDao smsPushDao;

    /**
     * 推送数据接口
     *
     * @param userId 用户id
     * @param messId 消息id
     * @return 成功：success
     */
    @ResponseBody
    @GetMapping("/socket/push/{userId}/{messId}")
    public String pushToWeb(@PathVariable String userId, @PathVariable String messId) {
        /* 根据userId在redis中获取sid */
        String sid = stringRedisTemplate.opsForValue().get(WebSocketServer.WEB_SOCKET_SESSION_ID_PREFIX + userId);
        /* 推送数据 */
        Optional<SmsPush> smsPushOptional = smsPushDao.findById(Long.valueOf(messId));
        if (!smsPushOptional.isPresent()) {
            /* 消息错误 */
            return "fail";
        }
        SmsPush smsPush = smsPushOptional.get();
        String smsPushJson = JSONUtil.parseObj(smsPush).toString();
        if (StrUtil.isBlank(sid)) {
            /* 用户未登录,将推送信息放入redis */
            stringRedisTemplate.opsForValue().set(WebSocketServer.WEB_SOCKET_PUSH_INFO_PREFIX + userId, smsPushJson);
            return "fail";
        }

        /* 发送消息 */
        WebSocketServer.sendInfo(smsPushJson, sid);
        return "success";
    }
}
