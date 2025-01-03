package com.demo.mapper;

import com.demo.domain.ElectronicTaxAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author classgeng
 * @since 2024-10-13
 */
@Mapper
public interface ElectronicTaxAccountMapper {

    List<ElectronicTaxAccount> selectAccountByCustCode(@Param("tenantCode") String tenantCode);

}
