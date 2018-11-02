package com.lihy.view.elasticsearch.service.impl;

import com.lihy.view.common.model.News;
import com.lihy.view.elasticsearch.mapper.NewsMapper;
import com.lihy.view.elasticsearch.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihy
 * @date 2018/10/31
 */
@Service("newService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> selectNews() {
        List<News> newsList = newsMapper.selectNews();
        return newsList;
    }
}
