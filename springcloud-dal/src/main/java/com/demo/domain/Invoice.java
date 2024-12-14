package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 发票信息
 * </p>
 *
 * @author classgeng
 * @since 2024-10-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Invoice extends BaseEntity implements Serializable {

    private Long id;

    /**
     * 勾票同步时间
     */
    private Date tickSyncTime;

    /**
     * 销方纳税人识别号
     */
    private String sellerTaxNo;

    /**
     * 销方单位名称
     */
    private String sellerName;

    /**
     * 销方银行名称
     */
    private String sellerBankName;

    /**
     * 销方银行账号
     */
    private String sellerBankAccount;

    /**
     * 销方地址
     */
    private String sellerAddress;

    /**
     * 销方电话
     */
    private String sellerPhone;

    /**
     * 购方纳税人识别号
     */
    private String purchaserTaxNo;

    /**
     * 购方单位名称
     */
    private String buyerName;

    /**
     * 购方银行名称
     */
    private String buyerBankName;

    /**
     * 购方银行账号
     */
    private String buyerBankAccount;

    /**
     * 购方地址
     */
    private String buyerAddress;

    /**
     * 购方电话
     */
    private String buyerPhone;

    /**
     * 开票人
     */
    private String drawer;

    /**
     * 复核人
     */
    private String checker;

    /**
     * 收款人
     */
    private String payee;

    /**
     * 开票流水号
     */
    private String serialNo;

    /**
     * 设备类型:0-百旺税控服务器;1-百旺税控盘;2-航天税控服务器;3-航天税控;4-ukey;5-全电;
     */
    private String deviceType;

    /**
     * 发票类型：蓝票 红票，取值参考 《系统配置》
     * 1001 - 蓝票
     * 1002 - 红票
     */
    private String invoiceType;

    /**
     * 发票种类编码，取值参考 《系统配置》
     * 1001 - 普票
     * 1002 - 专票
     */
    private String invoiceClassCode;

    /**
     * 机器编码(全电取消)
     */
    private String machineNo;

    /**
     * 发票代码(全电取消)
     */
    private String invoiceCode;

    /**
     * 发票号码
     */
    private String invoiceNo;

    /**
     * 原发票代码(全电取消)
     */
    private String originInvoiceCode;

    /**
     * 原发票号码(红冲)
     */
    private String originInvoiceNo;

    /**
     * 红字信息表编码
     */
    private String redInfoNo;

    /**
     * 开票日期 yyyy-MM-dd
     */
    private String invoiceDate;

    /**
     * 校验码(全电取消)
     */
    private String invoiceCheckCode;

    /**
     * 征税方式 参考《系统配置》
     */
    private String taxationMethod;

    /**
     * 合计金额。单位：元(不含税)
     */
    private BigDecimal invoiceTotalAmount;

    /**
     * 合计税额。单位：元
     */
    private BigDecimal invoiceTotalTax;

    /**
     * 价税合计(含税)
     */
    private BigDecimal invoiceTotalAmountTax;

    /**
     * 混合税率标识，默认0
     */
    private Integer mixedFlag;

    /**
     * 混合税率，用/分开
     */
    private String mixedTaxRate;
    /**
     * 综合税率
     */
    private BigDecimal compositeTaxRate;

    /**
     * 发票状态 参考《系统配置》正常、作废、已红冲、异常
     */
    private String invoiceStatus;

    /**
     * 勾票状态 参考《系统配置》未勾票、勾票中、已勾票
     */
    private String tickInvoiceStatus;

    /**
     * 勾票金额
     */
    private BigDecimal ticketAmount;

    /**
     * 配单人
     */
    private String ticketInvoiceBy;

    /**
     * 配单时间
     */
    private Date ticketInvoiceTime;

    /**
     * 记账金额
     */
    private BigDecimal accountingAmount;

    private Date accountingDate;

    /**
     * 记账状态 参考《系统配置》 已记账、未记账
     */
    private String accountingStatus;

    /**
     * 付款状态 参考《系统配置》 已记账、未记账
     */
    private String paymentStatus;

    /**
     * 付款状日期 参考《系统配置》
     */
    private Date paymentDate;

    /**
     * 付款金额
     */
    private BigDecimal paymentAmount;

    /**
     * 勾选认证状态 参考《系统配置》不可勾选、未勾选、勾选中、已勾选、勾选失败
     */
    private String tickAuthenticationStatus;

    /**
     * 查验状态 参考《系统配置》未查验、查验中、缺少查验字段、查验异常、查验失败、查无此票、查验成功
     */
    private String checkStatus;

    /**
     * 可抵扣税额。单位：元
     */
    private BigDecimal deductibleAmount;

    /**
     * 税款所属期 yyyyMM
     */
    private String taxPeriod;

    /**
     * 勾选认证成功时间,格式:yyyy-MM-dd HH:mm:ss
     */
    private String tickAuthenticationDate;

    /**
     * 签名确认时间,格式:yyyy-MM-dd HH:mm:ss
     */
    private String signConfirmDate;

    /**
     * 抵扣用途
     */
    private String deductionUse;

    /**
     * 勾选人
     */
    private String tickOperator;

    /**
     * 勾选方式：平台页面勾选、平台接口勾选、国税底账勾选
     */
    private String tickMode;

    /**
     * 底账获取状态 未获取、已获取
     */
    private String ledgerStatus;

    /**
     * 发票来源：国税验真、国税底账、销方直连
     */
    private String invoiceSource;

    /**
     * 源文件类型 ：OFD\PDF\JPG\无
     */
    private String fileType;

    /**
     * 打印状态
     */
    private Integer isPrint;

    /**
     * 勾票方式
     */
    private Integer tickManner;

    /**
     * 备注
     */
    private String invoiceRemarks;

    /**
     * 发票业务类型 2023/02/15新增
     */
    private String invoiceBusinessType;


    /**
     * 币种 2023/02/15新增
     */
    private String currency;

    /**
     * 合同编号 2023/02/15新增
     */
    private String contractNo;


    /**
     * po编号 2023/02/15新增
     */
    private String poNo;

    /**
     * 发票国家类别 （国产，进口）
     */
    private String invoiceCountryType;

    /**
     * 抵扣状态，0-不可抵扣，1-可抵扣
     */
    private String deductionStatus;

    /**
     * 是否取消勾选
     */
    private String isCancelTick;

    /**
     * 不抵扣原因
     */
    private String notDeductReason;

    /**
     * 勾选请求时间
     */
    private String tickRequestTime;

    /**
     * 记账人
     */
    private String accountant;

    /**
     * 销方ID
     */
    private String supplierId;

    /**
     * 签名状态
     */
    private String signConfirmStatus;

    /**
     * 已转出金额
     */
    private BigDecimal taxTransferredOutAmount;

    /**
     * 签收状态
     */
    private String signInStatus;

    /**
     * 签收人
     */
    private String signee;

    /**
     * 签收时间
     */
    private Date signInTime;

    /**
     * 退票状态
     */
    private String returnStatus;

    /**
     * 退票人
     */
    private String refunder;

    /**
     * 退票时间
     */
    private Date returnTime;

    /**
     * 退票备注
     */
    private String returnRemark;

    /**
     * 差价单标识 1 存在 0 不存在
     */
    private int priceGapFlag;

    /**
     * 已配单税额
     */
    private BigDecimal ticketTaxAmount;

    /**
     * 已配单不含税金额
     */
    private BigDecimal ticketAmountWithoutTax;

    /**
     * 勾票同步状态
     */
    private Integer tickSyncStatus;

    /**
     * 取消同步状态
     */
    private Integer cancelSyncStatus;

    /**
     * 问题发票标识：0-正常 1-标识
     */
    private Integer issueFlag;

    /**
     * 同步问题发票标识：0-正常 1-标识
     */
    private Integer syncIssueFlag;

    /**
     * 问题发票描述
     */
    private String issueDescription;

    /**
     * 同步失败原因
     */
    private String tickSyncFailReason;

    /**
     * 关联交易同步状态
     */
    private Integer tradeSyncStatus;

    /**
     * 累计勾票无税金额(中间字段)
     */
    private BigDecimal amount;

    /**
     * 累计勾票含税金额(中间字段)
     */
    private BigDecimal amountWithTax;

    /**
     * 累计勾票税额(中间字段)
     */
    private BigDecimal taxAmount;

    /**
     * 记账失败原因
     */
    private String accountingFailReason;

    /**
     * 租户
     */
    private String tenantId;

    /**
     * 发票代码和号码
     */
    private String InvoiceCodeNo;

    /**
     * 入账状态
     */
    private String entryStatus;

    /**
     * 入账时间 yyyy-MM-dd
     */
    private String entryDate;

    private String entryBy;

    private String twoInvoiceUploadStatus;

    private Integer twoInvoiceFlag;

    /**
     * 记账凭证号
     */
    private String tranNo;

    private String realInvoiceNo;

    private String realInvoiceCode;

    /**
     * pdf md5下载地址
     */
    private String pdfMd5;

    /**
     * ofd md5下载地址
     */
    private String ofdMd5;

    /**
     * xml md5下载地址
     */
    private String xmlMd5;

    /**
     * jpg md5下载地址
     */
    private String jpgMd5;


    @Getter
    @AllArgsConstructor
    public enum InvoiceStatus {
        /**
         * 发票状态：s1:正常；s2:红冲；s3:作废；s4:失控；s5:异常；
         * s6:认证异常；s7:红字待确认；s8:已部分冲红；s9:已全部红冲
         */
        s1("1001"),
        s2("1002"),
        s3("1003"),
        s4("1004"),
        s5("1005"),
        s6("1006"),
        s7("1007"),
        s8("1008"),
        s9("1009");
        /**
         * 统一编码
         */
        private String code;

        public static String getCode(String status) {
            return InvoiceStatus.valueOf("s" + status).getCode();
        }
    }

    @Getter
    @AllArgsConstructor
    public enum InvoiceClassCode {
        /**
         * 发票类型
         * c1001 - 增值税专用发票
         * c1002 - 增值税普通发票
         * c2001 - 增值税电子专用发票
         * c2002 - 增值税电子普通发票
         * c3001 - 数电票（增值税专用发票）
         * c3002 - 数电票（普票发票）
         * c3003 - 数电纸质发票（增值税专用发票）
         * c3004 - 数电纸质发票（普通发票）
         * c3005 - 数电票（航空运输电子客票行程单）
         * c3006 - 数电票（铁路电子客票）
         * c4001 - XYYD票
         * c4002 - G票
         * c4003 - X票
         * c4004 - X票增值税普通发票
         * c4005 - X票增值税专用发票 虚拟票
         * c4006 - 收据单 虚拟票
         * c4007 - 票货同行发票 虚拟票
         * c5001 - 机动车统一销售发票
         * c6001 - 二手车统一销售发票
         * c7001- 增值税普通发票（卷式）
         * c8001 - 道路通行费电子普通发票
         */
        C1001("1001"),
        C1002("1002"),
        C2001("2001"),
        C2002("2002"),
        C3001("3001"),
        C3002("3002"),
        C3003("3003"),
        C3004("3004"),
        C3005("3005"),
        C3006("3006"),
        XYYD("4001"),
        GPO("4002"),
        X("4003"),
        XP("4004"),
        XZ("4005"),
        X4006("4006"),
        X4007("4007"),
        C5001("5001"),
        C6001("6001"),
        C7001("7001"),
        C8001("8001"),
        ;

        /**
         * 统一编码
         */
        private String code;
    }

    @AllArgsConstructor
    @Getter
    public enum InvoiceAuthStatus {
        /**
         * 已认证
         */
        C1001("1001"),

        /**
         * 未认证
         */
        C1002("1002"),

        /**
         * 无需认证
         */
        C1008("1008");
        private String code;
    }

    /**
     * 配单同步状态
     */
    @AllArgsConstructor
    @Getter
    public enum TickSyncStatusEnum {
        /**
         * 待同步
         */
        TOBE_SYNC(0),

        /**
         * 同步中
         */
        SYNCING(1),
        /**
         * 同步成功
         */
        SYNC_SUCCESS(2),
        /**
         * 同步失败
         */
        SYNC_FAIL(3),
        /**
         * erp同步成功
         */
        SYNC_ERP_SUCCESS(4);

        private int code;
    }

    /**
     * 配单同步状态
     */
    @AllArgsConstructor
    @Getter
    public enum CancelSyncStatusEnum {
        /**
         * 待同步
         */
        TOBE_SYNC(0),

        /**
         * 同步中
         */
        SYNCING(1),
        /**
         * 同步成功
         */
        SYNC_SUCCESS(2),
        /**
         * 同步失败
         */
        SYNC_FAIL(3);

        private int code;
    }

    /**
     * 影像件是否已打印
     */
    @AllArgsConstructor
    @Getter
    public enum IsPrintEnum {

        ISPRINT(1,"已打印"),
        NOTPRINT(0,"未打印");

        private int code;
        private String desc;
    }
}
