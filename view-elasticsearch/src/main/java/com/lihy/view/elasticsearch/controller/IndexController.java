package com.lihy.view.elasticsearch.controller;

import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.elasticsearch.util.ElasticsearchRestClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author lihy
 * @date 2018/10/31
 */
@Controller
public class IndexController {
    @Autowired
    private ElasticsearchRestClient esRestUtil;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam("keyword") String keyword,
                         @RequestParam("pageNum") String pageNum,
                         @RequestParam("pageSize") String pageSize) {
        if (StringUtils.isBlank(keyword)) {
            return "index";
        }
        if (StringUtils.isBlank(pageNum)) {
            pageNum = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        int pageNumInt = Integer.parseInt(pageNum);
        int pageSizeInt = Integer.parseInt(pageSize);
        String[] searchFields = {"title", "content"};

        //ArrayList<Map<String, Object>> newsList = esRestUtil.searchNews("news", keyword, searchFields, 1, 10);
        ResponseResult<Map> responseResult = esRestUtil.searchNews("news", keyword, searchFields, pageNumInt, pageSizeInt);
        Map resultData = responseResult.getData();
        ArrayList<Map<String, Object>> newsList = (ArrayList<Map<String, Object>>) resultData.get("resultList");
        String totalHits = resultData.get("totalHits").toString();
        double totalTime = Double.valueOf(resultData.get("totalTime").toString())/1000.0;
        model.addAttribute("newsList", newsList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalHits", totalHits);
        model.addAttribute("totalTime", totalTime);
        model.addAttribute("pageNum", pageNum);
        return "result";
    }
}
