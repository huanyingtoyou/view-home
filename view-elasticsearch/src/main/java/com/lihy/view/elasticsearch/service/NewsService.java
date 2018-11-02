package com.lihy.view.elasticsearch.service;

import com.lihy.view.common.model.News;

import java.util.List;

/**
 * @author lihy
 * @date 2018/10/31
 */
public interface NewsService {
    List<News> selectNews();
}
