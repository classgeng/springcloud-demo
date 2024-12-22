package com.demo.task;


import com.demo.dto.UserInfo;
import com.demo.service.IDemoService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class PaginationQueryTask extends RecursiveTask<List<UserInfo>> {

    private Integer endPage;

    private Integer startPage;

    private IDemoService IDemoService;

    private String tenantId;

    private String systemSource;

    public PaginationQueryTask(String tenantId, String systemSource, int startPage, int endPage, IDemoService IDemoService) {
        this.tenantId = tenantId;
        this.systemSource = systemSource;
        this.startPage = startPage;
        this.endPage = endPage;
        this.IDemoService = IDemoService;
    }


    @Override
    protected List<UserInfo> compute() {
        if (endPage - startPage < 4) {
            List<UserInfo> list = new ArrayList<>();
            //直接查询,一页查询200条，一次直接查询2000条
            for (int pageNo = startPage; pageNo <= endPage; pageNo++) {
                List<UserInfo> invoicePageForExport;
                try {
                    invoicePageForExport = IDemoService.queryUserInfoList(tenantId,systemSource);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                list.addAll(invoicePageForExport);
            }
            log.info("UserInfo size: {}", list.size());
            return list;
        } else {
            // 如果任务大于10页，大于2000条，fork子线程去执行代码
            int midPage = (endPage + startPage) / 2;
            PaginationQueryTask left = new PaginationQueryTask(tenantId, systemSource, startPage, midPage, IDemoService);
            PaginationQueryTask right = new PaginationQueryTask(tenantId, systemSource, midPage + 1, endPage, IDemoService);
            invokeAll(left, right);
            List<UserInfo> leftJoin = left.join();
            leftJoin.addAll(right.join());
            log.info("fork UserInfo size: {}", leftJoin.size());
            return leftJoin;
        }
    }


}
