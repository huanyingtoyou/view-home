package com.lihy.view.user.mapper;

import com.lihy.view.common.entity.SysBillRule;
import com.lihy.view.common.entity.SysBillRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysBillRuleMapper {
    int countByExample(SysBillRuleExample example);

    int deleteByExample(SysBillRuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysBillRule record);

    int insertSelective(SysBillRule record);

    List<SysBillRule> selectByExample(SysBillRuleExample example);

    SysBillRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysBillRule record, @Param("example") SysBillRuleExample example);

    int updateByExample(@Param("record") SysBillRule record, @Param("example") SysBillRuleExample example);

    int updateByPrimaryKeySelective(SysBillRule record);

    int updateByPrimaryKey(SysBillRule record);

    /**
     * 更新单据编号
     * 获取按日期递增的单据号,根据有没有日期格式来判断是递增还是循环
     * @param rulesCode
     * @param dateTime
     * @return
     */
    SysBillRule getNewBillRule(@Param("rulesCode") String rulesCode, @Param("dateTime") String dateTime);
}