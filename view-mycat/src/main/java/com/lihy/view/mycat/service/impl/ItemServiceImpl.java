package com.lihy.view.mycat.service.impl;

import com.lihy.view.common.constant.SystemConstant;
import com.lihy.view.common.entity.Item;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.mycat.mapper.ItemMapper;
import com.lihy.view.mycat.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lihy
 * @date 2018/11/09
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ResponseResult<Item> getItemInfoById(Long id) {
        ResponseResult<Item> responseResult = new ResponseResult<>();
        responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
        responseResult.setResponseMsg(SystemConstant.SUCCESS.getName());
        Item item = itemMapper.selectByPrimaryKey(id);
        responseResult.setData(item);
        return responseResult;
    }

    @Override
    public ResponseResult<Void> addItem(Item item) {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
        responseResult.setResponseMsg(SystemConstant.SUCCESS.getName());
        int i = itemMapper.insertSelective(item);
        if (i < 0) {
            responseResult.setResponseCode(SystemConstant.ERROR.getCode());
            responseResult.setResponseMsg(SystemConstant.ERROR.getName());
        }
        return responseResult;
    }
}
