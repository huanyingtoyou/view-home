package com.lihy.view.common.constant;

/**
 * @author lihy
 * @date 2018/04/17
 */
public enum SystemConstant {

    SUCCESS                   ("00000", "成功"),
    ERROR                     ("99999", "失败"),
    REQ_PARAMS_ERR            ("01001", "请求参数不正确"),
    USER_IS_NULL              ("02001", "用户不存在"),
    PASSWORD_IS_ERROR         ("02002", "密码错误"),
    SERVICE_IS_UNABLE         ("03001", "服务不可用"),
    SERVICE_IS_ERROR          ("03002", "服务调用失败");
    private String code;
    private String name;

    SystemConstant(String code, String name){
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
