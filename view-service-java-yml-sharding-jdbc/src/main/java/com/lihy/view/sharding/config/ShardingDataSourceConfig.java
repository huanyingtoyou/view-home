package com.lihy.view.sharding.config;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这个有问题
 * 暂时不要测试
 * @author lihy
 * @date 2018/10/24
 */
@Configuration
public class ShardingDataSourceConfig {
    @Bean(name = "ds_0")
    @ConfigurationProperties(prefix = "spring.datasource.ds-0.hikari")
    public DataSource dataSource0() {
        return new HikariDataSource();
    }

    @Bean(name = "ds_1")
    @ConfigurationProperties(prefix = "spring.datasource.ds-1.hikari")
    public DataSource dataSource1() {
        return new HikariDataSource();
    }

    @Primary
    @Bean(name = "shardingDataSource")
    public DataSource getDataSource(@Qualifier("ds_0") DataSource ds_0, @Qualifier("ds_1") DataSource ds_1) throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new DatabaseShardingAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new TablePreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("ds_0", ds_0);
        dataSourceMap.put("ds_1", ds_1);
        Properties properties = new Properties();
        //properties.setProperty("sql.show", Boolean.TRUE.toString());
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap<>(), properties);
    }

    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order");
        result.setActualDataNodes("ds_${0..1}.t_order_${[0, 1]}");
        result.setKeyGeneratorColumnName("order_id");
        return result;
    }

    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("t_order_item");
        result.setActualDataNodes("ds_${0..1}.t_order_item_${[0, 1]}");
        return result;
    }
}
