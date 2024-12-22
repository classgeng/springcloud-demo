package com.demo.service.impl;

import com.demo.common.excel.EasyExcelUtils;
import com.demo.common.util.UUIDUtils;
import com.demo.dto.UserInfo;
import com.demo.service.IDemoService;
import com.demo.task.PaginationQueryTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Slf4j
@Service
public class IDemoServiceImpl implements IDemoService {

    @Override
    public void test(String str) {
        log.info("hello {}", str);
    }

    @Async("threadPoolExecutor")
    @Override
    public void exportExcel(String tenantId, String systemSource) {
        List<UserInfo> userInfoList = parallelComputingUserInfoList(tenantId, systemSource);
        String filePath = "D:\\test\\";
        String fileName = UUIDUtils.getUUID() + ".xlsx";
        String sheetName = "用户信息";
        EasyExcelUtils.write(userInfoList, UserInfo.class, filePath+fileName, sheetName);
        log.info("exportExcel success");
    }

    private List<UserInfo> parallelComputingUserInfoList(String tenantId, String systemSource) {
        int  totalPage = 10;
        //fork join并行计算
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);
        PaginationQueryTask paginationQueryTask = new PaginationQueryTask(tenantId, systemSource, 1, totalPage, this);
        forkJoinPool.invoke(paginationQueryTask);
        //得到需要写入excel的数据
        return paginationQueryTask.join();
    }

    @Override
    public List<UserInfo> queryUserInfoList(String tenantId, String systemSource) throws InterruptedException {
        Thread.sleep(30000);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userInfoList.add(new UserInfo(UUIDUtils.getUUID(),"demo", 18));
        }
        log.info("queryUserInfoList size:{}", userInfoList.size());
        return userInfoList;
    }

}
