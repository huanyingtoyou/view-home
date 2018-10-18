package com.lihy.view.user.service.impl;

import com.lihy.view.common.constant.SystemConstant;
import com.lihy.view.common.entity.SysBillRule;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.user.mapper.SysBillRuleMapper;
import com.lihy.view.user.service.SysBillRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

/**
 * @author lihy
 * @date 2018/09/07
 */
@Service("sysBillRuleService")
public class SysBillRuleServiceImpl implements SysBillRuleService {
    @Autowired
    private SysBillRuleMapper sysBillRuleMapper;

    /**
     * 更新单据编号
     * 获取按日期递增的单据号,根据有没有日期格式来判断是递增还是循环
     * @param rulesCode
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ResponseResult<String> doBillRuleByRulesCode(String rulesCode) {
        ResponseResult<String> result = new ResponseResult<>();
        result.setResponseCode(SystemConstant.SUCCESS.getCode());
        result.setResponseMsg(SystemConstant.SUCCESS.getName());
        synchronized (rulesCode) {
            String dateTime = String.valueOf(System.currentTimeMillis());
            SysBillRule newBillRule = sysBillRuleMapper.getNewBillRule(rulesCode,dateTime);
            if (null != newBillRule) {
                if(!dateTime.equals(newBillRule.getUpdateUserId())){
                    return this.doBillRuleByRulesCode(rulesCode);
                }
                result.setData(getCurrentValue(newBillRule));
                return result;
            }
        }
        return result;
    }

    private String getCurrentValue(SysBillRule sysBillRule) {
        if (null != sysBillRule.getPrefix() && !"".equals(sysBillRule.getPrefix())) {
            if (null != sysBillRule.getDateType() && !"".equals(sysBillRule.getDateType())) {
                return sysBillRule.getPrefix() + sysBillRule.getDateString()
                        + format(sysBillRule.getCurrentValue(), sysBillRule.getItemLength());
            } else {
                return sysBillRule.getPrefix()
                        + format(sysBillRule.getCurrentValue(), sysBillRule.getItemLength());
            }
        } else if (null != sysBillRule.getDateType() && !"".equals(sysBillRule.getDateType())) {
            return sysBillRule.getDateString()
                    + format(sysBillRule.getCurrentValue(), sysBillRule.getItemLength());
        } else {
            return String.valueOf(sysBillRule.getCurrentValue());
        }
    }

    /**
     *
     * 格式化数字
     * @param value
     * @param num
     * @return
     */
    private String format(Integer value, Integer num) {
        DecimalFormat df = null;
        char[] chs = new char[num.intValue()];
        for (int i = 0; i < num; i++) {
            chs[i] = '0';
        }
        df = new DecimalFormat(new String(chs));
        return df.format(value);
    }
}
