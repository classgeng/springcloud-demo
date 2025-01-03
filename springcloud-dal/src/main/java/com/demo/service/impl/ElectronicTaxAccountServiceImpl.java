package com.demo.service.impl;

import com.demo.domain.ElectronicTaxAccount;
import com.demo.mapper.ElectronicTaxAccountMapper;
import com.demo.service.ElectronicTaxAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ElectronicTaxAccountServiceImpl implements ElectronicTaxAccountService {

    @Resource
    private ElectronicTaxAccountMapper electronicTaxAccountMapper;

    @Override
    public List<ElectronicTaxAccount> selectAccountByCustCode(String tenantCode) {
        return electronicTaxAccountMapper.selectAccountByCustCode(tenantCode);
    }
}
