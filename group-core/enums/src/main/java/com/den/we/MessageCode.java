package com.den.we;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 *  编码枚举
 *
 *【规则说明】
 *    5位编码规则=[2位模块]+[3位序号]
 *
 *  《类型》：
 *    权限类错误=1
 *    请求类错误=2（参数、请求类型等）
 *    用户模块=3

 *
 *    eg：
 *    1、权限登陆错误 =3001
 *    2、请求参数错误=4001
 *
 *《注意》
 *    0 = 成功
 *    500 = 未知错误
 *    429   请求太频繁，请稍后再试
 *    999   系统升级
 *    4000  未授权的访问
 *    3000  参数绑定错误(如:必须参数没传递)
 *    6000  数据过期，请刷新重试
 *
 * @author fatKarin
 * @time 2019.01.23 16:22
 */
@AllArgsConstructor
@Getter
public enum MessageCode {
    SUCCESS(0, "SUCCESS", "成功"),
    ERROR(500, "ERROR", "未知错误"),


    //权限类错误=1
    INVALID_AUTH_TOKEN(10000, "INVALID-AUTH-TOKEN", "无效的访问令牌"),


    //请求类错误=2（参数、请求类型等）
    REQUEST_BAD(20000, "REQUEST_BAD", "请求错误"),
    INVALID_PARAMETER(20001, "INVALID_PARAMETER", "参数无效"),
    REQUIRED_PARAMETER(20002, "REQUIRED_PARAMETER", "参数不能为空"),
    MISSING_PHONE(20003, "MISSING_PHONE", "缺少手机号码"),
    BAD_FORMAT_PHONE(20004, "BAD_FORMAT_PHONE", "错误的电话号码格式"),
    INCORRECT_PASSWORD(20005, "INCORRECT_PASSWORD", "密码错误"),


    //用户模块=3
    MISSING_USER(30000, "MISSING_USER","用户不存在"),
    ACCOUNT_DISABLE(30001, "ACCOUNT_DISABLE", "该帐号已经被禁用"),
    TAG_ALREADY_EXIT(30002,"TAG_ALREADY_EXIT", "该标签已经存在"),
    USER_NOT_EXIT(30003, "USER_NOT_EXIT", "用户不存在"),

    //社区模块=4
    UPLOAD_FILE_NOT_BE_NULL(40000,"UPLOAD_FILE_NOT_BE_NULL","上传文件不能为空"),
    UPLOAD_FAILED(40001, "UPLOAD_FAILED", "上传失败"),
    SQL_EXECUTE_FAILED(40002, "SQL_EXECUTE_FAILED", "SQL执行失败"),

    //消息推送模块=5
    PUBLISH_FAILED(50000,"PUBLISH_FAILED", "发布消息失败"),

    //后台管理模块=6
    TAG_ALREADY_EXITED(60001,"TAG_ALREADY_EXITED","标签名称已经存在"),
    ;


    /**
     * 编码
     */
    private int code;

    /**
     * 英文编码
     */
    private String enCode;

    /**
     * 编码中文描述
     */
    private String desc;


    /**
     * 转换为枚举
     * @param name 枚举名称
     * @return
     */
    public static Optional<MessageCode> convertToOptionalMessageCode(String name){
        return Arrays.stream(MessageCode.values()).filter(e -> e.name().equals(name)).findFirst();
    }

    /**
     * 转换为枚举
     * @param name 枚举名称
     * @return 转换成功则返回转换后的枚举值，转换失败则返回指定的枚举值
     */
    public static MessageCode convertToMessageCode(String name, MessageCode defaultMessageCode){
        Optional<MessageCode> messageCodeOptional = convertToOptionalMessageCode(name);
        return messageCodeOptional.orElse(defaultMessageCode);

    }

    /**
     * 转换为枚举
     * @param name 枚举名称
     * @return 转换成功则返回转换后的枚举值，转换失败则返回未知错误的枚举值
     */
    public static MessageCode convertToMessageCode(String name){
        return convertToMessageCode(name, MessageCode.ERROR);
    }
}
