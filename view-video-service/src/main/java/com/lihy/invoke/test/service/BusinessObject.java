package com.lihy.invoke.test.service;

import com.lihy.invoke.test.source.Component;
import com.lihy.invoke.test.dao.DataAccessInterface;
import com.lihy.invoke.test.source.Autowire;

/**
 * service层
 * 调用dao层
 * @author lihy
 * @date 2018/09/29
 */
@Component(id="businessObject")
public class BusinessObject {
    @Autowire(id = "dataAccessInterface")
    private DataAccessInterface dataAccessInterface;

    public void print() {
        System.out.println(dataAccessInterface.queryFormTable());
    }

    public void setDataAccessInterface(DataAccessInterface dataAccessInterface) {
        this.dataAccessInterface = dataAccessInterface;
    }
}
