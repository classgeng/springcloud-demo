package com.demo.mq.producer;

import com.demo.common.domain.MessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MessageSender<T> {

    @Resource
    private MessageChannel output;

    public void send(MessageInfo<T> message) {
        log.info("发送消息：{}", message);
        this.output.send(MessageBuilder.withPayload(message).build());
    }

    public void sendByTags(MessageInfo<T> message, String tagsValue) {
        log.info("发送TAGS消息：{}", message);
        Message<MessageInfo<T>> msg = MessageBuilder.withPayload(message).setHeader(MessageConst.PROPERTY_TAGS, tagsValue).build();
        this.output.send(msg);
    }

    public void sendByKeys(MessageInfo<T> message, String keysValue) {
        log.info("发送KEYS消息：{}", message);
        Message<MessageInfo<T>> msg = MessageBuilder.withPayload(message).setHeader(MessageConst.PROPERTY_KEYS, keysValue).build();
        this.output.send(msg);
    }

}
