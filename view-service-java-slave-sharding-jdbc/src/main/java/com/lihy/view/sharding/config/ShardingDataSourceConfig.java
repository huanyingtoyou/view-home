package com.lihy.view.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import io.shardingsphere.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 根据官网示例写的java示例
 * @author lihy
 * @date 2018/10/26
 */
@Configuration
public class ShardingDataSourceConfig {
    //@Primary
    @Bean(name = "shardingDataSource")
    public DataSource getShardingDataSource() throws SQLException {
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new DatabaseShardingAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new TablePreciseShardingAlgorithm(), new TableRangeShardingAlgorithm()));
        //增加读写分离规则
        shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new ConcurrentHashMap(), new Properties());
    }

    /**
     * 需要手动配置事务管理器
     * @param shardingDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(shardingDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/sharding/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_${0..1}");
        return orderTableRuleConfig;
    }

    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
        orderItemTableRuleConfig.setLogicTable("t_order_item");
        orderItemTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_item_${0..1}");
        return orderItemTableRuleConfig;
    }

    /**
     * 主从数据源规则配置
     * @return
     */
    List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig1 = new MasterSlaveRuleConfiguration("ds_0", "demo_ds_master_0", Arrays.asList("demo_ds_master_0_slave_0"));
        MasterSlaveRuleConfiguration masterSlaveRuleConfig2 = new MasterSlaveRuleConfiguration("ds_1", "demo_ds_master_1", Arrays.asList("demo_ds_master_1_slave_0"));
        return Lists.newArrayList(masterSlaveRuleConfig1, masterSlaveRuleConfig2);
    }

    /**
     * 封装数据源map
     * @return
     */
    private static Map<String, DataSource> createDataSourceMap() {
        final Map<String, DataSource> result = new HashMap<>();
        result.put("demo_ds_master_0", buildDataSourceConfig("localhost", "demo_ds_master_0", "root", ""));
        result.put("demo_ds_master_0_slave_0", buildDataSourceConfig("192.168.137.131", "demo_ds_master_0", "root", "jtwmyDTSGX#520"));
        result.put("demo_ds_master_1", buildDataSourceConfig("localhost", "demo_ds_master_1", "root", ""));
        result.put("demo_ds_master_1_slave_0", buildDataSourceConfig("192.168.137.131", "demo_ds_master_1", "root", "jtwmyDTSGX#520"));
        return result;
    }

    /**
     * 数据源配置
     * @param ip
     * @param databaseName
     * @param username
     * @param password
     * @return
     */
    private static DataSource buildDataSourceConfig(final String ip, final String databaseName, final String username, final String password) {
        //使用druid连接数据库
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl(String.format("jdbc:mysql://%s:3306/%s?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false", ip , databaseName));
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }
}
