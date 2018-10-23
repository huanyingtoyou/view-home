package com.lihy.view.elk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihy
 * @date 2018/10/23
 */
@RestController
public class ElkController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElkController.class);

    @GetMapping("/testName/{id}")
    public String getTestName(@PathVariable String id){
        LOGGER.info("-------------this is a test log-------------");
        return id;
    }
}
