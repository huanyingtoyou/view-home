package com.lihy.view.mycat.controller;

import com.lihy.view.common.entity.Item;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.mycat.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihy
 * @date 2018/11/09
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 根据id获取item表信息
     * @param id
     * @return
     */
    @GetMapping(value = "/getItemInfoById/{id}")
    public ResponseResult<Item> getItemInfoById(@PathVariable Long id) {
        ResponseResult<Item> responseResult = itemService.getItemInfoById(id);
        return responseResult;
    }

    /**
     * 新增item
     * @param item
     * @return
     */
    @PostMapping("/addItem")
    public ResponseResult<Void> addItem(@RequestBody Item item) {
        ResponseResult<Void> responseResult = itemService.addItem(item);
        return responseResult;
    }
}
