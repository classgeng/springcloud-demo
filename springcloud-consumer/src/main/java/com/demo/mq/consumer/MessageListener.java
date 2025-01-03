package com.demo.mq.consumer;

import com.demo.common.domain.MessageInfo;
import com.demo.mq.channel.ConsumerSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener<T> {

    @StreamListener(ConsumerSink.INPUT_TAGS)
    public void receiveTags(Message<MessageInfo<T>> message) {
        log.info("消费TAGS消息：{}", message.toString());
    }

    @StreamListener(ConsumerSink.INPUT_KEYS)
    public void receiveKeys(Message<MessageInfo<T>> message) {
        log.info("消费KEYS消息：{}", message.toString());
    }

}
