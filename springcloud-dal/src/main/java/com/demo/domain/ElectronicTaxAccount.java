package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import java.io.Serializable;
import java.util.Date;

/**
 * 电子税局登录Entity
 */
@Data
public class ElectronicTaxAccount extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 企业税号
     */
    private String enterpriseTaxNo;

    /**
     * 报税省份
     */
    private String taxReportProvince;

    /**
     * 报税省份拼音全称
     */
    private String taxReportProvincePyCode;

    /**
     * 报税省份代码
     */
    private String taxReportProvinceCode;

    /**
     * 登录角色代码
     */
    private String roleCode;

    /**
     * 登录角色名称
     */
    private String roleName;

    /**
     * 登录角色手机号
     */
    private String phone;

    /**
     * 登录角色身份证号
     */
    private String idNumber;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String passWord;

    /**
     * 验证码
     */
    private String smsCode;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    /**
     * 登录状态
     * 在线 - ONLINE
     * 离线 - OFFLINE
     */
    private String loginStatus;

    /**
     * 百旺登录状态
     * 在线 - ONLINE
     * 离线 - OFFLINE
     */
    private String baiwangLoginStatus;
    /**
     * 百旺登录的更新时间
     */
    private Date baiwangLoginTime;
    /**
     * 使用状态
     * 0 - 在用
     * 1 - 未用
     */
    private String useStatus;

    /**
     * 操作标志位
     *
     */
    private String handleFlag;

    /**
     * redis获取登录状态 - md5key
     */
    private String loginStatusKey;


    /**
     * 开票终端代码
     */
    private String invoiceTerminalCode;

    /**
     * 办税人姓名
     */
    private String taxpayerName;

    /**
     * 用户代码
     */
    private String custCode;

    /**
     * 开票通道
     */
    private String channel;

    /**
     * 开票模式
     */
    private String invoiceMode;

    /**
     * 抵扣账号标识 0-不抵扣 1-抵扣
     */
    private int deductFlag;

    /**
     * 上次账号掉线告警时间
     */
    private Date lastOfflineWarnTime;

    @Getter
    @AllArgsConstructor
    public enum LOGIN_STATUS {
        ONLINE("ONLINE"),
        OFFLINE("OFFLINE");
        private String code;
    }

    @Getter
    @AllArgsConstructor
    public enum USE_STATUS {
        USE("0"),
        UN_USE("1");
        private String code;
    }

    @Getter
    @AllArgsConstructor
    public enum HANDLE_FLAG_ENUM {
        CREATE("create"),
        UPDATE("update"),
        LOGIN("login");
        private String code;
    }

    @Getter
    @AllArgsConstructor
    public enum ROLE_CODE_ENUM {
        R1("1"),//法人
        R2("2"),//财务负责人
        R3("3"),//办税人
        R4("4"),//购票员
        R5("5"); //开票员
        private String code;
    }
}

