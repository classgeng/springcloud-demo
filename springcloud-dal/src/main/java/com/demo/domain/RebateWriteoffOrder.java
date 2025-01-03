package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 返利核销单
 *
 * @author classgeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RebateWriteoffOrder extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 返利核销单ID
     */
    private String writeOffId;

    /**
     * 对账金额
     */
    private BigDecimal accountAmount;

    /**
     * 本次核销金额
     */
    private BigDecimal auditAmount;

    /**
     * 已核销金额
     */
    private BigDecimal writeOffAmt;

    /**
     * 未核销金额
     */
    private BigDecimal unWriteOffAmt;

    /**
     * 状态：0-无效，1-保存，2-提交
     */
    private Integer status;

    /**
     * 台账附件，json格式
     */
    private String accountAttachment;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 协议编码
     */
    private String agreementCode;

    /**
     * 协议名称
     */
    private String agreementName;

    /**
     * 政策编码
     */
    private String policyCode;

    /**
     * 政策名称
     */
    private String policyName;

    /**
     * 政策创建人
     */
    private String policyCreateBy;

    /**
     * 责任采购员名称
     */
    private String responsibleBuyer;

    /**
     * 协议账期开始日期
     */
    private String agreementAccountDateBeginDate;

    /**
     * 协议账期结束日期
     */
    private String agreementAccountDateEndDate;

    /**
     * 返利类型
     */
    private String rebateType;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 公司ID
     */
    private String ou;

    /**
     * 业态
     */
    private String systemSource;

    /**
     * 发票代码
     */
    private String invoiceCode;

    /**
     * 发票号码
     */
    private String invoiceNo;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 单据号
     */
    private String priceGapOrderNumber;

    @Getter
    @AllArgsConstructor
    public enum StatusType {
        C0(0),//无效
        C1(1),//保存
        C2(2);//提交
        private final int code;
    }

}
