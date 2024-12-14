package com.demo.common.domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liyicheng
 */
@Data
public class Response {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String code;
    private String message;
    private Object data;
    private String time;


    public static Response success() {
        Response response01 = new Response();
        response01.setCode(ResponseCode.SUCCESS.getCode());
        response01.setMessage(ResponseCode.SUCCESS.getMessage());
        response01.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response01;
    }

    public static Response fail(){
        Response response01 = new Response();
        response01.setCode(ResponseCode.FAIL.getCode());
        response01.setMessage(ResponseCode.FAIL.getMessage());
        response01.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response01;
    }

    public static Response pending(){
        Response response01 = new Response();
        response01.setCode(ResponseCode.PENDING.getCode());
        response01.setMessage(ResponseCode.PENDING.getMessage());
         return response01;
    }


    public static Response fail(String e) {
        Response response01 = new Response();
        response01.setCode(ResponseCode.FAIL.getCode());
        response01.setMessage(e);
        response01.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response01;
    }

    public static Response fail(String code,String e) {
        Response response01 = new Response();
        response01.setCode(code);
        response01.setMessage(e);
        response01.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response01;
    }

    public static Response success(String message) {
        Response response01 = new Response();
        response01.setCode(ResponseCode.SUCCESS.getCode());
        response01.setMessage(message);
        response01.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response01;
    }

    public static Response fail(ResponseCode responseCode,String message) {
        Response response01 = new Response();
        response01.setCode(responseCode.getCode());
        response01.setMessage(message);
        response01.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response01;
    }
    public static Response failWithData(String message, Object data) {
        Response response01 = new Response();
        response01.setCode(ResponseCode.FAIL.getCode());
        response01.setMessage(message);
        response01.setData(data);
        response01.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response01;
    }

    public static Response fail(ResponseCode responseCode) {
        Response response01 = new Response();
        response01.setCode(responseCode.getCode());
        response01.setMessage(responseCode.getMessage());
        response01.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response01;
    }

    public boolean isSuccess() {
        return ResponseCode.SUCCESS.getCode().equals(this.code);
    }

}
