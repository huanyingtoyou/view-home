package com.lihy.view.common.constant;

/**
 * @author lihy
 * @date 2018/04/17
 */
public enum SystemConstant {

    SUCCESS                   ("00000", "成功"),
    ERROR                     ("99999", "失败"),
    REQ_PARAMS_ERR            ("01001", "请求参数不正确"),
    USER_IS_NULL              ("01002", "用户不存在"),
    SERVICE_IS_UNABLE         ("01003", "服务不可用"),
    SERVICE_IS_ERROR          ("01003", "服务调用失败");
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
