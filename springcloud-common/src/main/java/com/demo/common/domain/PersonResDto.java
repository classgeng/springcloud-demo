package com.demo.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonResDto {

    /**
     * 人员信息主键 int8
     */
    @JsonProperty(index = 0)
    private Long id;
    /**
     * 人员编码 varchar
     */
    @JsonProperty(index = 10)
    private String code;
    /**
     * 姓名 varchar
     */
    @JsonProperty(index = 20)
    private String name;
    /**
     * 性别  10-男，20-女 int4
     */
    @JsonProperty(index = 30)
    private Integer sex;
    /**
     * 籍贯 varchar
     */
    @JsonProperty(index = 40)
    private String nationality;

    /**
     * 省
     */
    @JsonProperty(index = 40)
    private String provice;

    /**
     * 市
     */
    @JsonProperty(index = 40)
    private String city;

    /**
     * 省 文本值
     */
    @JsonProperty(index = 40)
    private String proviceText;

    /**
     * 市 文本值
     */
    @JsonProperty(index = 40)
    private String cityText;
    /**
     * 出生日期 date
     */
    @JsonProperty(index = 50)
    private String birthday;
    /**
     * 邮箱 varchar
     */
    @JsonProperty(index = 60)
    private String email;
    /**
     * 手机号 varchar
     */
    @JsonProperty(index = 70)
    private String phone;
    /**
     * 备用邮箱 varchar
     */
    @JsonProperty(index = 80)
    private String backEmail;
    /**
     * 备用手机 varchar
     */
    @JsonProperty(index = 90)
    private String backPhone;
    /**
     * 座机(办公电话) varchar
     */
    @JsonProperty(index = 100)
    private String landline;
    /**
     * 身份证号 varchar
     */
    @JsonProperty(index = 110)
    private String identityCard;
    /**
     * 人员类别 varchar
     */
    @JsonProperty(index = 120)
    private String personType;
    /**
     * 农历生日 date
     */
    @JsonProperty(index = 130)
    private String lunarBirthday;
    /**
     * 称呼 varchar
     */
    @JsonProperty(index = 140)
    private String callName;
    /**
     * 意见领袖 int4
     */
    @JsonProperty(index = 150)
    private Integer commitLeader;
    /**
     * 评吸人员 int4
     */
    @JsonProperty(index = 160)
    private Integer assessor;
    /**
     * 资料来源 varchar
     */
    @JsonProperty(index = 170)
    private String infoSource;
    /**
     * 助记码 varchar
     */
    @JsonProperty(index = 180)
    private String mnemonicCode;
    /**
     * UID varchar
     */
    @JsonProperty(index = 190)

    private String uid;
    /**
     * 文化程度 varchar
     */
    @JsonProperty(index = 200)
    private String educationDegree;
    /**
     * 系统内码 varchar
     */
    @JsonProperty(index = 210)
    private String sysInternalCode;
    /**
     * 专业名称 varchar
     */
    @JsonProperty(index = 220)
    private String majorName;
    /**
     * HRID varchar
     */
    @JsonProperty(index = 230)
    private String hrId;
    /**
     * 组织ID int8
     */
    @JsonProperty(index = 240)
    private Long orgId;
    /**
     * 数据来源 MDM-10，平台自建-20 int2
     */
    @JsonProperty(index = 250)
    private Integer dataSource;

    /**
     * 创建人用户ID int8
     */
    @JsonProperty(index = 270)
    private Long createUserId;
    /**
     * 创建人用户名 varchar
     */
    @JsonProperty(index = 280)
    private String createUserName;
    /**
     * 创建时间 timestamp
     */
    @JsonProperty(index = 290)
    private String createTime;
    /**
     * 修改人用户ID int8
     */
    @JsonProperty(index = 300)

    private Long modifyUserId;
    /**
     * 修改人用户名 varchar
     */
    @JsonProperty(index = 310)

    private String modifyUserName;
    /**
     * 修改时间 timestamp
     */
    @JsonProperty(index = 320)
    private String modifyTime;
    /**
     * bg int8
     */
    @JsonProperty(index = 350)

    private Long bg;


    @JsonProperty(index = 544)
    private String idPathFullName;

    private String idPath;

    /**
     * 人员性别文本形式
     */
    @JsonProperty(index = 550)
    private String sexText;

    /**
     * 人员信息类别文本形式
     */
    @JsonProperty(index = 560)
    private String personTypeText;

    /**
     * 数据来源文本形式
     */
    @JsonProperty(index = 560)
    private String dataSourceText;

    /**
     * 账户名
     */
    @JsonProperty(index = 570)
    private String username;

    @JsonProperty(index = 580)
    private Long accountId;



}
