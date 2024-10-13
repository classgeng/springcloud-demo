package com.demo.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liyicheng
 */

@Getter
@AllArgsConstructor
public enum ResponseCode {
    /** 成功 */
    SUCCESS("000000","请求成功"),

    CALL_BACK_SUCCESS("200","请求成功"),

    REPEAT_CALL_BACK_SUCCESS("300","请求成功"),
    USER_INFO_FAIL("401", "获取用户信息失败，请重新登录，或者联系管理员！"),
    /** 失败 */
    FAIL("-1", "请求失败"),
    PENDING("1000", "任务执行中"),

    PARAMETER_ERROR("100000","参数错误"),
    TOKEN_APP_ID_MISS("100001","请输入正确的token或者appId"),
    TOKEN_ERROR("100002","token错误"),

    IP_FORBIDDEN("100003","ip禁止访问"),

    APP_ID_ERROR("100004","APP_ID不正确"),

    TOKEN_EXPIRED("100005","token已过期"),
    TOKEN_INVALID("100006","非法的token"),
    TAX_NO_MISS("100007","请选择您要查询的税号"),


    TAX_WITHOUT_PERMISSION("100008","税号没有权限"),
    PERMISSION_DENIED ("100009", "当前用户没有权限"),

    WITHOUT_TENANT ("100010", "当前用户不是租户"),
    GKSK_TENANT_MISS("100011","请选择租户!"),
    GKSK_TOKEN_MISS("100012","缺少请求token!"),

    GKSK_GET_CURRENT_PERSON_FAIL("401","解析用户中心token失败,请尝试重新登录！"),
    GKSK_GET_DEFAULT_USER_FAIL("401","获取用户中心默认用户信息失败,请尝试重新登录!"),
    GKSK_GET_TENANT_USER_FAIL("401","您不能使用该租户编码,请尝试重新登录!"),

    GKSK_GET_DETAIL_USER_FAIL("403","您还不是平台用户,请联系管理员添加用户，进行授权!"),

    GKSK_GET_INVOICE_TENANT_FAIL("403","您还不是发票平台用户,请联系管理员添加用户，进行授权!"),

    GKSK_GET_INVOICE_COMPANY_FAIL("403","您还未配置组织,请联系管理员配置!"),
    GKSK_GET_INVOICE_MENU_FAIL("403","您还未配置操作角色,请联系管理员配置!"),
    GKSK_GET_INVOICE_TENANT_CROSS("403","您在多个租户中,请联系发票运维人员处理！租户分别为："),

    BIZ_TYPE_NO_MISS("100015","请选择系统来源"),

    TOKEN_TIME_OUT("100016","token超时"),
    /**
     * 全局异常处理
     * */
    SERVER_INTERNAL_ERROR  ("200001","服务器内部异常"),
    PARAMETER_VERIFY_EXCEPTION  ("200002","参数校验异常"),

    DUPLICATE_KEY_EXCEPTION("200003","数据库重复异常"),
    REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("200004","请求方法不支持异常"),
    MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION("200005","文件大小超出10MB限制, 请压缩或降低文件质量!"),
    DATA_INTEGER_VIOLATION_EXCEPTION("200006","执行数据库异常,违反了完整性例如：违反惟一约束、违反非空限制、字段内容超出长度等"),
    REDIS_EXCEPTION("200007","Redis 连接异常!"),
    NO_HANDLER_FOUND_EXCEPTION  ("200008","路径不存在，请检查路径是否正"),

    REQUEST_PARAMETER_CONVERSION_EXCEPTION("200009","http请求参数转换异常"),

    EXIST_SERIAL_NO("200010","重复的流水号"),

    FILE_PARSE_EXCEPTION("200011","文件解析异常"),
    FILE_COMPRESS_EXCEPTION("200012","文件压缩失败"),

    SEND_EMAIL_SUCCESS("200013","邮件发送成功"),
    SEND_EMAIL_FAIL("200014","邮件发送失败："),
    SEND_PHONE_SUCCESS("200015","短信发送成功"),
    SEND_PHONE_FAIL("200016","短信发送失败："),

    REMOTE_INVOKE_FAIL("200017","远程调用失败"),

    NOT_FOUND_CONFIG("200018","找不到第三方配置"),

    DATA_EXCHANGE_INVOKE_FAIL("200019","DataExchange调用失败"),

   TENANTId_EXCEPTION("200020","JWT-tenantId Header-tenantId不一致"),

    ;


    /** 代码*/
    private String code;
    /** 成功 */
    private String message;

}
