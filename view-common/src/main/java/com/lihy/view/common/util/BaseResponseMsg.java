package com.lihy.view.common.util;

import java.io.Serializable;

/**
 * @author lihy
 * @date 2018/04/17
 */
public class BaseResponseMsg implements Serializable {
    private String responseCode;
    private String responseMsg;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
