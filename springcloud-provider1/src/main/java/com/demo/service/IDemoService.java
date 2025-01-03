package com.demo.service;

import com.demo.dto.UserInfo;

import java.util.List;

public interface IDemoService {

    void test(String str);

    void exportExcel(String tenantId,String systemSource);

    List<UserInfo> queryUserInfoList(String tenantId, String systemSource) throws InterruptedException;

}
