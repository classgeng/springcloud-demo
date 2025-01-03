package com.demo.mapper;

import com.demo.domain.RebateWriteoffOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author classgeng
 * @since 2024-11-20
 */
@Mapper
public interface RebateWriteoffOrderMapper {

    List<RebateWriteoffOrder> queryRebateWriteoffOrderList(@Param("priceGapOrderIds") List<Long> priceGapOrderIds);

}
