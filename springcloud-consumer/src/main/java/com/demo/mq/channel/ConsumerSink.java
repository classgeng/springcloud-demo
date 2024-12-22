package com.demo.mq.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerSink {

    String INPUT_TAGS = "inputTags";
    String INPUT_KEYS = "inputKeys";

    @Input(ConsumerSink.INPUT_TAGS)
    SubscribableChannel inputTags();

    @Input(ConsumerSink.INPUT_KEYS)
    SubscribableChannel inputKeys();

}
