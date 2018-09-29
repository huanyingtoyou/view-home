package com.lihy.invoke.test;

/**
 * @author lihy
 * @date 2018/09/29
 */
@Component(id = "dataAccessInterface")
public class DataAccessInterface {
    public String queryFormTable() {
        return "query result";
    }
}
