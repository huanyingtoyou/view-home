package com.lihy.view.elasticsearch.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lihy.view.common.model.News;
import com.lihy.view.elasticsearch.service.NewsService;
import com.lihy.view.elasticsearch.util.ElasticsearchRestClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihy
 * @date 2018/10/31
 */
@Component
public class ElasticsearchApplicationRunner implements ApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(ElasticsearchApplicationRunner.class);

    @Autowired
    private ElasticsearchRestClient elasticsearchRestClient;

    @Autowired
    private NewsService newService;

    @Override
    public void run(ApplicationArguments var) throws Exception {
        //删除userdoc索引
        elasticsearchRestClient.deleteIndex("news");

        //设置mapping
        XContentBuilder builder = null;
        builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("title");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                }
                builder.endObject();
                builder.startObject("content");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                    builder.field("term_vector", "with_positions_offsets");
                }
                builder.endObject();
                builder.startObject("url");
                {
                    builder.field("type", "text");
                }
                builder.endObject();
                builder.startObject("postdate");
                {
                    builder.field("type", "date");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();

        //初始化索引
        Boolean isSuccess = elasticsearchRestClient.initIndex("news", "data", 3, 0, builder);

        if (isSuccess) {
            logger.info("索引初始化成功.索引名: userdoc,类型名: file,分片数: 3,副本数: 0");
            //将mysql数据同步导入到elasticsearch
            List<News> newList = newService.selectNews();
            ArrayList<String> news = new ArrayList<>();
            ObjectMapper objMapper = new ObjectMapper();
            if (newList != null && newList.size() > 0) {
                for (int i=0; i < newList.size(); i++) {
                    String json = objMapper.writeValueAsString(newList.get(i));
                    news.add(json);
                }
            }
            elasticsearchRestClient.indexNews("news", "data", news);
        } else {
            logger.error("索引初始化失败.");
        }
    }
}
