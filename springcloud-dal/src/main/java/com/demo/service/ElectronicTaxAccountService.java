package com.demo.service;

import com.demo.domain.ElectronicTaxAccount;
import com.demo.domain.Invoice;
import java.util.List;

public interface ElectronicTaxAccountService {

    /**
     * 根据条件分页查询发票列表
     *
     * @param tenantCode 发票信息
     * @return 发票信息集合
     */
    List<ElectronicTaxAccount> selectAccountByCustCode(String tenantCode);

}
