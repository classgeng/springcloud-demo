package com.demo.common;

import com.demo.common.util.OkHttpClientUtils;

import java.math.BigDecimal;
import java.util.*;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.ss.formula.functions.T;

public class Main {

    public static void main(String[] args) {

       /* String url = "http://openapisit.guoyaoshuke.com/openapi/guokongrebate/api/gksk-rebate-openapi/invoice-writeoff/account/confirm/addBillId";
        String params = "";
        //添加票据
        /*String url = "http://openapisit.guoyaoshuke.com/openapi/guokongrebate/api/gksk-rebate-openapi/invoice-writeoff/account/confirm/addBillId";
        Map<String, String> headers = new HashMap<String, String>();
        //headers.put("Content-Type", "application/json");
        headers.put("X-HMAC-ALGORITHM", "hmac-sha256");
        headers.put("X-HMAC-ACCESS-KEY", "developer_invoice-management");
        headers.put("X-HMAC-SIGNATURE", "moNaNvESzuor01FM7qnFzmA/rAq18CrxisleXqOIM6I=");
        headers.put("Date", "Mon, 25 Nov 2024 09:37:18 GMT");
        headers.put("gksk-busi-form", "1224");
        headers.put("gksk-company-id", "546547987349509");
        headers.put("gksk-org-id", "587562590539781");
        headers.put("gksk-owner-id", "18");
        headers.put("gksk-tenant-code", "guoyao-lerentang");
        headers.put("gksk-tenant-id", "1224");
        headers.put("gksk-user-id", "500601473253381");
        headers.put("gksk-access-token", "invoice");
        String params = null;*/

        /*PersonResDto prsonResDto = queryPerson();
        if(null == prsonResDto) {
            System.out.println("工号不存在");
        } else {
            System.out.println(prsonResDto.getCode());
        }*/

        /*double dd = StrUtil.similar("你好1223", "你好1223");
        System.out.println(dd);*/

        Date date = new Date();
        long section = DateUtil.between(date, DateUtil.endOfMonth(date), DateUnit.DAY);
        System.out.println(section);
        System.out.println(Math.toIntExact(section));


    }

    public static PersonResDto queryPerson(){
        //String url = "http://paasgw.guoyaoshuke.com//api/user-manage/person/one";
        String domain = "http://uat.guoyaoshuke.com";
        String path = "api/user-manage/person/one";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("gksk-tenant-id", "1014");
        headers.put("gksk-tenant-code", "guokong-xinjiang");
        headers.put("gksk-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjU4NjE2MjY2ODE5MTc0OSwidW4iOiLogL_ljY_plIsiLCJleHAiOjMzMjU5ODgzMDM0LCJpYXQiOjE3MzQ1OTY2MzQsIm5kdSI6NjI0NDc3MTEzNjI0ODM3fQ.g8CyvySdF662jg76Lcja9BYvGjIZxwLJhpk5-LgH7PM");
        String str = HttpClientUtils.doPost(url, params, headers);
        System.out.println(str);*/

        //OkHttpClientUtils.doPostJson(url, params, headers);


        /*String url = "https://sit.guoyaoshuke.com/api/sinopharm-invoice-purchase/rebate/account/getToken";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-HMAC-ALGORITHM", "hmac-sha256");
        headers.put("X-HMAC-ACCESS-KEY", "developer_invoice-management");
        headers.put("X-HMAC-SIGNATURE", "moNaNvESzuor01FM7qnFzmA/rAq18CrxisleXqOIM6I=");
        headers.put("Date", "Mon, 25 Nov 2024 09:37:18 GMT");
        headers.put("gksk-busi-form", "1220");
        headers.put("gksk-company-id", "549772888203269");
        headers.put("gksk-org-id", "549773005152261");
        headers.put("gksk-owner-id", "13");
        headers.put("gksk-tenant-code", "guokong-chongqingtaimin");
        headers.put("gksk-tenant-id", "1220");
        headers.put("gksk-user-id", "500601473253381");
        headers.put("taxno", "91500225MA60QCHP57");
        headers.put("gksk-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjU4NjE2MjY2ODE5MTc0OSwidW4iOiLogL_ljY_plIsiLCJleHAiOjMzMjU3ODAyNjI2LCJpYXQiOjE3MzI1MTYyMjYsIm5kdSI6NjE1OTU1NzYxMTM3MzQ5fQ.hlSdqs6H1OXzH7dgLy1uhed_3Wt1qVNhq9UH1xer8RI");

        OkHttpClientUtils.doGetRequest(url, null, headers);*/

        //String str = HttpClientUtils.httpGet(url, headers);
        //System.out.println(str);


        long hours = DateUtil.between(new Date(), DateTime.now(), DateUnit.HOUR);
        System.out.println(hours);

        Map<String, String> params = new HashMap<>();
        params.put("code", "11406767");

        String url = domain.endsWith("/") ? domain+path : domain+"/"+path;
        String result = OkHttpClientUtils.doGetRequest(url, params, headers);
        System.out.println(result);
        Type type = new TypeToken<Response<PersonResDto>>() {}.getType();
        Response<PersonResDto> response = JsonUtils.fromJsonToObj(result, type);
        if("0".equals(response.getCode())){
            return response.getData();
        } else {
            throw new RuntimeException("查询人员信息失败");
        }
    }




}
