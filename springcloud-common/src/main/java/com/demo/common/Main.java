package com.demo.common;

import com.demo.common.util.OkHttpClientUtils;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String url = "http://openapisit.guoyaoshuke.com/openapi/guokongrebate/api/gksk-rebate-openapi/invoice-writeoff/account/confirm/addBillId";
        String params = null;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
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

        OkHttpClientUtils.doPostJson(url, params, headers);

    }


}
