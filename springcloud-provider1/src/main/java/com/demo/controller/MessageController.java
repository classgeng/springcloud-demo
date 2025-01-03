package com.demo.controller;

import com.demo.common.domain.MessageInfo;
import com.demo.common.domain.Response;
import com.demo.common.util.UUIDUtils;
import com.demo.mq.producer.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author classgeng
 * @date 2020-11-16 15:16
 */
@Slf4j
@RestController
@RequestMapping("/api/msg")
public class MessageController {

    @Resource
    private MessageSender<String> messageSender;

    /**
     * 测试服务
     * @return
     */
    @RequestMapping(path="/send")
    public Response<String> send(String message) {
        MessageInfo<String> msg = new MessageInfo<>();
        msg.setMessageId(UUIDUtils.getUUID());
        msg.setMessageCode("classgeng");

        msg.setData(message + "-tags");
        messageSender.sendByTags(msg, "classgeng-tags");
        msg.setData(message + "-keys");
        messageSender.sendByKeys(msg, "classgeng-keys");

        return Response.success("ok");
    }


}
