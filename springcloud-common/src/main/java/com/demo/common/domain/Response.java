package com.demo.common.domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liyicheng
 */
@Data
public class Response<T> {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String code;
    private String message;
    private T data;
    private String time;


    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response;
    }

    public static <T> Response<T> fail(){
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.FAIL.getCode());
        response.setMessage(ResponseCode.FAIL.getMessage());
        response.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response;
    }

    public static <T> Response<T> pending(){
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.PENDING.getCode());
        response.setMessage(ResponseCode.PENDING.getMessage());
         return response;
    }


    public static <T> Response<T> fail(String e) {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.FAIL.getCode());
        response.setMessage(e);
        response.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response;
    }

    public static <T> Response<T> fail(String code,String e) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(e);
        response.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response;
    }

    public static <T> Response<T> success(String message) {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(message);
        response.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response;
    }

    public static <T> Response<T> fail(ResponseCode responseCode,String message) {
        Response<T> response = new Response<>();
        response.setCode(responseCode.getCode());
        response.setMessage(message);
        response.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response;
    }
    public static <T> Response<T> failWithData(String message, T data) {
        Response<T> response = new Response<>();
        response.setCode(ResponseCode.FAIL.getCode());
        response.setMessage(message);
        response.setData(data);
        response.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response;
    }

    public static <T> Response<T> fail(ResponseCode responseCode) {
        Response<T> response = new Response<>();
        response.setCode(responseCode.getCode());
        response.setMessage(responseCode.getMessage());
        response.setTime(SIMPLE_DATE_FORMAT.format(new Date()));
        return response;
    }

    public boolean isSuccess() {
        return ResponseCode.SUCCESS.getCode().equals(this.code);
    }

}
