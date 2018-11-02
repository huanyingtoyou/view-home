package com.lihy.view.elasticsearch.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lihy
 * @date 2018/10/31
 */
@Component
@ConfigurationProperties(prefix = "elastic.search")
@Setter
@Getter
@ToString
public class ElasticsearchConfig {
    private String host;
    private int port;
    private String clusterName;
}
