package com.lihy.view.common.util;

/**
 * @author lihy
 * @date 2018/04/17
 */
public class ResponseResult<T> extends BaseResponseMsg {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
