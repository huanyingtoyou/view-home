package com.lihy.view.mycat.service;

import com.lihy.view.common.entity.Item;
import com.lihy.view.common.util.ResponseResult;

/**
 * @author lihy
 * @date 2018/11/09
 */
public interface ItemService {
    ResponseResult<Item> getItemInfoById(Long id);
    ResponseResult<Void> addItem(Item item);
}
