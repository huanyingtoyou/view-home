package com.lihy.view.user.service;

import com.lihy.view.common.util.ResponseResult;

/**
 * @author lihy
 * @date 2018/09/07
 */
public interface SysBillRuleService {
    /**
     * 更新单据编号
     * 获取按日期递增的单据号,根据有没有日期格式来判断是递增还是循环
     * @param rulesCode
     * @return
     */
    ResponseResult<String> doBillRuleByRulesCode(String rulesCode);
}
