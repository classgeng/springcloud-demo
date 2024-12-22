package com.demo.common.domain;

import lombok.Data;

@Data
public class MessageInfo<T> {

    private String messageId;

    private String messageCode;

    private T data;

}
